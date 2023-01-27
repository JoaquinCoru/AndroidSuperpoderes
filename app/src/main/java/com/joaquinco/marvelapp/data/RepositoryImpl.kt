package com.joaquinco.marvelapp.data

import com.joaquinco.marvelapp.data.local.LocalDataSource
import com.joaquinco.marvelapp.data.mappers.LocalToPresentationMapper
import com.joaquinco.marvelapp.data.mappers.RemoteToLocalMapper
import com.joaquinco.marvelapp.data.mappers.RemoteToPresentationMapper
import com.joaquinco.marvelapp.data.remote.RemoteDataSource
import com.joaquinco.marvelapp.domain.MarvelCharacter
import com.joaquinco.marvelapp.domain.MarvelSerie
import com.joaquinco.marvelapp.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val remoteToLocalMapper: RemoteToLocalMapper,
    private val localToPresentationMapper: LocalToPresentationMapper,
    private val remoteToPresentationMapper: RemoteToPresentationMapper
): Repository {

    override suspend fun getCharactersWithCache(): Flow<List<MarvelCharacter>> {
        // 1º Compruebo si hay datos en local
        if (localDataSource.getNumberOfCharacters()==0) {
            //2 Pido datos a remoto
            val remoteCharacters = remoteDataSource.getCharacters()
            //3 Guardo datos en local
            localDataSource.insertCharacters(remoteToLocalMapper.map(remoteCharacters))
        }
        // 4º Devuelvo datos local
        return localDataSource.getCharacters().map { localToPresentationMapper.map(it) }
    }

    override suspend fun setLike(character: MarvelCharacter) {
        localDataSource.updateCharacter(localToPresentationMapper.map(character))
    }

    override suspend fun getSeries(characterId: Int): Flow<List<MarvelSerie>> {
        return remoteDataSource.getSeries(characterId).map { remoteToPresentationMapper.mapSeries(it) }
    }

}