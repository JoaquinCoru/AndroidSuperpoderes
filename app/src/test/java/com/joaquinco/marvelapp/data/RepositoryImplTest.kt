package com.joaquinco.marvelapp.data

import app.cash.turbine.test
import com.joaquinco.marvelapp.data.local.model.MVCharacterLocal
import com.joaquinco.marvelapp.data.mappers.LocalToPresentationMapper
import com.joaquinco.marvelapp.data.mappers.RemoteToLocalMapper
import com.joaquinco.marvelapp.data.mappers.RemoteToPresentationMapper
import com.joaquinco.marvelapp.domain.MarvelCharacter
import com.joaquinco.marvelapp.fake.FakeLocalDataSource
import com.joaquinco.marvelapp.fake.FakeRemoteDataSource
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local

class RepositoryImplTest {

    //UUT o SUT
    private  lateinit var repositoryImpl: RepositoryImpl
    val fakeRemoteDataSource = FakeRemoteDataSource()
    val remoteToLocalMapper = RemoteToLocalMapper()
    val localToPresentationMapper = LocalToPresentationMapper()
    val remoteToPresentationMapper = RemoteToPresentationMapper()


    @Test
    fun `WHEN get Characters EXPECT valid data`() = runTest {
        // GIVEN
        repositoryImpl = RepositoryImpl(
            FakeLocalDataSource(),
            fakeRemoteDataSource,
            remoteToLocalMapper,
            localToPresentationMapper,
            remoteToPresentationMapper
        )

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
            fakeLocalDataSource.emit(listOf(MVCharacterLocal("id","name","photo")))
            assert(awaitItem()[0].name == "name")

            fakeLocalDataSource.emit(listOf(MVCharacterLocal("id1","name1","photo1")))
            assert(awaitItem()[0].name == "name1")

        }
    }
}


/*
  Usamos Fakes para poder tener implementaciones reales de las dependencias y poder tener más control
  de las interacciones cada una. Como por ejemplo tengamos una dependencia del localDataSource que llamemos
  dos veces y que en  un primer momento queramos que devuelva lista vacía y en otro con datos.

  En otros casos que no necesitemos tanto control de las interacciones con las dependencias usamos Mocks
  como algunos tests del ViewModel
 */