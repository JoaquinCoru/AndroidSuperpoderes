package com.joaquinco.marvelapp.data.remote

import com.joaquinco.marvelapp.domain.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getCharacters(): MarvelResponse

    suspend fun getSeries(characterId:Int): Flow<MarvelResponse>
}