package com.joaquinco.marvelapp.data.local

import com.joaquinco.marvelapp.data.local.model.MVCharacterLocal
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getCharacters(): Flow<List<MVCharacterLocal>>
    fun insertCharacters(characters: List<MVCharacterLocal>)
    fun getNumberOfCharacters():Int
    fun updateCharacter(character: MVCharacterLocal)
}