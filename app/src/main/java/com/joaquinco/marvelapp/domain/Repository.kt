package com.joaquinco.marvelapp.domain

import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCharactersWithCache(): Flow<List<MarvelCharacter>>
    suspend fun setLike(character: MarvelCharacter)
    suspend fun getSeries(characterId:Int): Flow<List<MarvelSerie>>
}