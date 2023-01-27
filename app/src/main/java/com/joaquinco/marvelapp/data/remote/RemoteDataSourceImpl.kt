package com.joaquinco.marvelapp.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: MarvelAPI): RemoteDataSource  {
    override suspend fun getCharacters(): MarvelResponse {

        return api.getCharacters()
//        return flow { emit(result) }
    }

    override suspend fun getSeries(characterId: Int): Flow<MarvelResponse> {
        return flow { emit(api.getSeries(characterId)) }
    }
}