package com.joaquinco.marvelapp.ui.characterList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.common.truth.Truth
import com.joaquinco.marvelapp.domain.MarvelCharacter
import com.joaquinco.marvelapp.domain.Repository
import com.joaquinco.marvelapp.utils.generateLocalCharactersFlow
import com.joaquinco.marvelapp.utils.generatePresentationCharactersFlow
import com.joaquinco.marvelapp.utils.generatePresentationListCharacters
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // SUT o UUT
    private lateinit var characterListViewModel: CharacterListViewModel

    // Dependencies
    private lateinit var repository: Repository

    private val mainThreadSubrrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSubrrogate)
    }

    @Test
    fun `WHen getCharacters expects return list of characters`() = runTest {
        // GIVEN
        repository = mockk()
        coEvery { repository.getCharactersWithCache() } returns generatePresentationCharactersFlow()
        characterListViewModel = CharacterListViewModel(repository)

        // WHEN
        val actualList = mutableListOf<List<MarvelCharacter>>()
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            characterListViewModel.characters.toList(actualList)
        }
        characterListViewModel.getCharacters()

        // THEN
        assert(
            actualList.size == 1
        )

        Truth.assertThat(actualList).containsAtLeastElementsIn(listOf(generatePresentationListCharacters()) )

        collectJob.cancel()
    }

    @Test
    fun `When setLike in ViewModel is called repository setLike is called`() = runTest {
        // GIVEN
        repository = mockk()
        coEvery { repository.setLike(any()) } returns Unit
        coEvery { repository.getCharactersWithCache() } returns generatePresentationCharactersFlow()
        characterListViewModel = CharacterListViewModel(repository)

        // WHEN
        characterListViewModel.setLike(MarvelCharacter("id","name","photo"))

        //THEN
        coVerify { repository.setLike(any()) }

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSubrrogate.close()
    }
}