package com.joaquinco.marvelapp.data.remote

import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: MarvelAPI): RemoteDataSource  {
    override suspend fun getCharacters(): MarvelResponse {

        return api.getCharacters()
    }
}