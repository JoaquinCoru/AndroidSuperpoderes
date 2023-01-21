package com.joaquinco.marvelapp.data.remote

interface RemoteDataSource {

    suspend fun getCharacters():MarvelResponse
}