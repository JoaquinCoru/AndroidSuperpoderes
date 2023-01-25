package com.joaquinco.marvelapp.data.remote

import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getCharacters(): MarvelResponse
}