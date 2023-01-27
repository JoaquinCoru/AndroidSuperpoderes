package com.joaquinco.marvelapp.data.mappers

import com.joaquinco.marvelapp.data.local.model.MVCharacterLocal
import com.joaquinco.marvelapp.data.remote.MarvelResponse
import com.joaquinco.marvelapp.domain.MarvelCharacter
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor() {

    fun map(marvelResponse: MarvelResponse): List<MVCharacterLocal> {
        return marvelResponse.data.results.map {
            MVCharacterLocal(
                it.id.toString(),
                it?.name ?: "",
                it.thumbnail.getImageUrl()
            )
        }
    }
}