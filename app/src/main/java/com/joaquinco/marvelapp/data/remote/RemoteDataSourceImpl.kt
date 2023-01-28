package com.joaquinco.marvelapp.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: MarvelAPI): RemoteDataSource  {
    override suspend fun getCharacters(): MarvelResponse {
        return api.getCharacters()
    }

    override suspend fun getSeries(characterId: Int): Flow<MarvelResponse> {
        return flow { emit(api.getSeries(characterId)) }
    }

    override suspend fun getComics(characterId: Int): Flow<MarvelResponse> {
        return flow { emit(api.getComics(characterId)) }
    }
}