package com.joaquinco.marvelapp.data.mappers

import com.joaquinco.marvelapp.data.local.model.MVCharacterLocal
import com.joaquinco.marvelapp.data.remote.MarvelResponse
import com.joaquinco.marvelapp.domain.MarvelCharacter
import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor() {

    fun map(characterList: List<MVCharacterLocal>): List<MarvelCharacter> {
        return characterList.map { map(it) }
    }

    fun map(character:MVCharacterLocal): MarvelCharacter {
        return MarvelCharacter(character.id,character.name,character.photo, character.isFavorite)
    }

    fun map(character:MarvelCharacter): MVCharacterLocal {
        return MVCharacterLocal(character.id,character.name,character.photo,character.isFavorite)
    }
}