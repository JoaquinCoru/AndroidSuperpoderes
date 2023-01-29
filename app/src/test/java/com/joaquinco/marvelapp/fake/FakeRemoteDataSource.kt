package com.joaquinco.marvelapp.fake

import com.joaquinco.marvelapp.data.remote.*
import com.joaquinco.marvelapp.domain.MarvelCharacter
import com.joaquinco.marvelapp.utils.generateFakeMarvelResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeRemoteDataSource() : RemoteDataSource {


    private val marvelResponseFake = generateFakeMarvelResponse()

    override suspend fun getCharacters(): MarvelResponse {
        return marvelResponseFake
    }

    override suspend fun getSeries(characterId: Int): Flow<MarvelResponse> {
        return flow { marvelResponseFake }
    }

    override suspend fun getComics(characterId: Int): Flow<MarvelResponse> {
        return  flow { marvelResponseFake }
    }

}