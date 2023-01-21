package com.joaquinco.marvelapp.data.remote

import retrofit2.http.GET

interface MarvelAPI {

    @GET("v1/public/characters")
    suspend fun getCharacters():MarvelResponse
}