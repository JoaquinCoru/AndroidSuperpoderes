package com.joaquinco.marvelapp.fake

import com.joaquinco.marvelapp.data.local.LocalDataSource
import com.joaquinco.marvelapp.data.local.model.MVCharacterLocal
import com.joaquinco.marvelapp.utils.generateLocalCharactersFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeLocalDataSource(private val shared: Boolean = false): LocalDataSource {
    private var firstCall = true

    private val sharedFlow = MutableSharedFlow<List<MVCharacterLocal>>()

    suspend fun emit(list: List<MVCharacterLocal>){
        sharedFlow.emit(list)
    }

    override fun getCharacters(): Flow<List<MVCharacterLocal>> {
        return if (shared) {
            sharedFlow
        } else {
            generateLocalCharactersFlow()
        }
    }

    override fun insertCharacters(characters: List<MVCharacterLocal>) {

    }

    override fun getNumberOfCharacters(): Int {
        return if (firstCall) {
            firstCall = false
            0
        }else {
            1
        }
    }

    override fun updateCharacter(character: MVCharacterLocal) {
        TODO("Not yet implemented")
    }

}