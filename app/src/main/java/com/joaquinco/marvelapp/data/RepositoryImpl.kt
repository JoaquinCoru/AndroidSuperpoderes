package com.joaquinco.marvelapp.data

import com.joaquinco.marvelapp.data.remote.MarvelResponse
import com.joaquinco.marvelapp.data.remote.RemoteDataSource
import com.joaquinco.marvelapp.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): Repository {

    override suspend fun getCharacters(): MarvelResponse {
        return remoteDataSource.getCharacters()
    }
}