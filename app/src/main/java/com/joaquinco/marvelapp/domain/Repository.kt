package com.joaquinco.marvelapp.domain

import com.joaquinco.marvelapp.data.remote.MarvelResponse
import kotlinx.coroutines.flow.Flow
import java.lang.Character

interface Repository {
    suspend fun getCharacters(): Flow<List<MarvelCharacter>>

}