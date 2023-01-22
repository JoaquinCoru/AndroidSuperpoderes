package com.joaquinco.marvelapp.data.mappers

import com.joaquinco.marvelapp.data.remote.MarvelResponse
import com.joaquinco.marvelapp.domain.MarvelCharacter
import javax.inject.Inject

class RemoteToPresentationMapper @Inject constructor() {

    fun map(marvelResponse: MarvelResponse): List<MarvelCharacter> {
        return marvelResponse.data.results.map {
            MarvelCharacter(
                it.id.toString(),
                it.name,
                it.thumbnail.getImageUrl()
            )
        }
    }
}