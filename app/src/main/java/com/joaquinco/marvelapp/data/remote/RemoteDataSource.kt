package com.joaquinco.marvelapp.data.remote

import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getCharacters(): MarvelResponse

    suspend fun getSeries(characterId:Int): Flow<MarvelResponse>

    suspend fun getComics(characterId: Int): Flow<MarvelResponse>
}