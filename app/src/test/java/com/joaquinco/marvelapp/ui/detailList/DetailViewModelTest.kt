package com.joaquinco.marvelapp.ui.detailList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.joaquinco.marvelapp.domain.MarvelSerie
import com.joaquinco.marvelapp.domain.Repository
import com.joaquinco.marvelapp.utils.generatePresentationListCharacters
import com.joaquinco.marvelapp.utils.generatePresentationListSeries
import com.joaquinco.marvelapp.utils.generatePresentationSeriesFlow
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // SUT o UUT
    private lateinit var detailViewModel: DetailViewModel

    // Dependencies
    private lateinit var repository: Repository

    private val mainThreadSubrrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSubrrogate)
    }

    @Test
    fun `When getSeries expects return valid list`() = runTest {
        // GIVEN
        repository = mockk()
        detailViewModel = DetailViewModel(repository)
        coEvery { repository.getSeries(any()) } returns generatePresentationSeriesFlow()

        // WHEN
        detailViewModel.getSeries(1)
        val actualList = mutableListOf<List<MarvelSerie>>()
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            detailViewModel.series.toList(actualList)
        }


        assert(actualList.size == 1)

        Truth.assertThat(actualList).containsAtLeastElementsIn(listOf(
            generatePresentationListSeries()
        ) )

        collectJob.cancel()
    }

}