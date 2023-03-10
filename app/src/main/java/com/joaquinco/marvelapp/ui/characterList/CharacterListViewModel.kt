package com.joaquinco.marvelapp.ui.characterList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaquinco.marvelapp.domain.MarvelCharacter
import com.joaquinco.marvelapp.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    companion object {
        private val TAG = "ListViewModel: "
    }

    private val _characters = MutableStateFlow(emptyList<MarvelCharacter>())
    val characters: StateFlow<List<MarvelCharacter>> get() = _characters

    init {
        getCharacters()
    }

    fun getCharacters() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.getCharactersWithCache().flowOn(Dispatchers.IO).collect {
//                Log.d(TAG, it.toString())
                _characters.value = it
            }

        }
    }

    fun setLike(character: MarvelCharacter){

//        character.isFavorite = isFavorite

        viewModelScope.launch(Dispatchers.IO) {

            repository.setLike(character)
        }
    }

}