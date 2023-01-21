package com.joaquinco.marvelapp.domain

import com.joaquinco.marvelapp.data.remote.MarvelResponse

interface Repository {
    suspend fun getCharacters():MarvelResponse

}