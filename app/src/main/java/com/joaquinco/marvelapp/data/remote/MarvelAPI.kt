package com.joaquinco.marvelapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelAPI {

    @GET("v1/public/characters")
    suspend fun getCharacters():MarvelResponse

    @GET("v1/public/characters/{characterId}/series")
    suspend fun getSeries(@Path("characterId") characterId: Int):MarvelResponse

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getComics(@Path("characterId") characterId: Int):MarvelResponse
}