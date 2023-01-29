package com.joaquinco.marvelapp.data

import app.cash.turbine.test
import com.joaquinco.marvelapp.data.local.LocalDataSource
import com.joaquinco.marvelapp.data.local.model.MVCharacterLocal
import com.joaquinco.marvelapp.data.mappers.LocalToPresentationMapper
import com.joaquinco.marvelapp.data.mappers.RemoteToLocalMapper
import com.joaquinco.marvelapp.data.mappers.RemoteToPresentationMapper
import com.joaquinco.marvelapp.data.remote.RemoteDataSource
import com.joaquinco.marvelapp.fake.FakeLocalDataSource
import com.joaquinco.marvelapp.fake.FakeRemoteDataSource
import com.joaquinco.marvelapp.utils.generateFakeMarvelResponse
import com.joaquinco.marvelapp.utils.generateLocalCharactersFlow
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/*
  Usamos Fakes para poder tener implementaciones reales de las dependencias y poder tener más control
  de las interacciones cada una. Como por ejemplo tengamos una dependencia del localDataSource que llamemos
  dos veces y que en  un primer momento queramos que devuelva lista vacía y en otro con datos.

  En otros casos que no necesitemos tanto control de las interacciones con las dependencias usamos Mocks
  como algunos tests del ViewModel
 */

class RepositoryImplTest {

    //UUT o SUT
    private lateinit var repositoryImpl: RepositoryImpl
    val fakeRemoteDataSource = FakeRemoteDataSource()
    val fakeLocalDataSource = FakeLocalDataSource()
    val remoteToLocalMapper = RemoteToLocalMapper()
    val localToPresentationMapper = LocalToPresentationMapper()
    val remoteToPresentationMapper = RemoteToPresentationMapper()

    @Before
    fun setUp() {
        repositoryImpl = RepositoryImpl(
            fakeLocalDataSource,
            fakeRemoteDataSource,
            remoteToLocalMapper,
            localToPresentationMapper,
            remoteToPresentationMapper
        )

    }

    @Test
    fun `WHEN get Characters EXPECT valid data`() = runTest {
        // GIVEN

        // WHEN
        val actual = repositoryImpl.getCharactersWithCache().toList()

        // THEN
        assert(actual[0][0].name == "name")
    }

    @Test
    fun `WHEN get Characters then emit 2 values Expect valid data`() = runTest {
        // GIVEN
        val fakeLocalDataSource = FakeLocalDataSource(true)
        repositoryImpl = RepositoryImpl(
            fakeLocalDataSource,
            fakeRemoteDataSource,
            remoteToLocalMapper,
            localToPresentationMapper,
            remoteToPresentationMapper
        )

        // WHEN
        val actual = repositoryImpl.getCharactersWithCache()

        // THEN
        actual.test {
            fakeLocalDataSource.emit(listOf(MVCharacterLocal("id", "name", "photo")))
            assert(awaitItem()[0].name == "name")

            fakeLocalDataSource.emit(listOf(MVCharacterLocal("id1", "name1", "photo1")))
            assert(awaitItem()[0].name == "name1")

        }
    }

    /*
        En este utilizamos mocks en el remote para comprobar cuantas veces se está llamando un método al obtener
        la lista de personajes cacheados
     */
    @Test
    fun `WHEN getCharacters return list from local and remote is called`()= runTest {
        // GIVEN
        val remoteDataSource: RemoteDataSource = mockk()
        val localDataSource: LocalDataSource = mockk()
        repositoryImpl = RepositoryImpl(
            localDataSource,
            remoteDataSource,
            remoteToLocalMapper,
            localToPresentationMapper,
            remoteToPresentationMapper
        )
        coEvery { localDataSource.getNumberOfCharacters() } returns 0
        coEvery { localDataSource.insertCharacters(any()) } returns Unit
        coEvery { localDataSource.getCharacters() } returns  generateLocalCharactersFlow()
        coEvery { remoteDataSource.getCharacters() } returns generateFakeMarvelResponse()

        // WHEN
        repositoryImpl.getCharactersWithCache()

        // THEN
        coVerify { remoteDataSource.getCharacters() }
        coVerify { localDataSource.insertCharacters(any()) }
        coVerify { localDataSource.getCharacters() }
    }

    @Test
    fun `WHEN getComics EXPEXT valid data`() = runTest {
        // GIVEN

        // WHEN
        val actual = repositoryImpl.getComics(1).toList()

        // THEN
        assert(actual[0][0].title == "title")
    }

}

