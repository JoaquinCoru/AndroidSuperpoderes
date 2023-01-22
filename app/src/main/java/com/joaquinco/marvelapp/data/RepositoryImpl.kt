package com.joaquinco.marvelapp.data

import com.joaquinco.marvelapp.data.mappers.RemoteToPresentationMapper
import com.joaquinco.marvelapp.data.remote.RemoteDataSource
import com.joaquinco.marvelapp.domain.MarvelCharacter
import com.joaquinco.marvelapp.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val remoteToPresentationMapper: RemoteToPresentationMapper
): Repository {

    override suspend fun getCharacters(): Flow<List<MarvelCharacter>> {
        return remoteDataSource.getCharacters().map { marvelResponse -> remoteToPresentationMapper.map(marvelResponse) }
    }
}