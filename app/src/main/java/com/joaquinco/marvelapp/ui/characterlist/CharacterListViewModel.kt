package com.joaquinco.marvelapp.ui.characterlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaquinco.marvelapp.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    companion object {
        private val TAG = "ListViewModel: "
    }

    init {
        getCharacters()
    }

    fun getCharacters() {

        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getCharacters()
            }

            Log.d(TAG, response.toString())
        }
    }

}