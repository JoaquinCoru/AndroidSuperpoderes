package com.joaquinco.marvelapp.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: MarvelAPI): RemoteDataSource  {
    override suspend fun getCharacters(): Flow<MarvelResponse> {

        val result = api.getCharacters()
        return flow { emit(result) }
    }
}