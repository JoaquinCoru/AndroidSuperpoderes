package com.joaquinco.marvelapp.ui.detailList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaquinco.marvelapp.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel(){

    companion object {
        private val TAG = "DetailViewModel: "
    }

    fun getSeries(characterId: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.getSeries(characterId).flowOn(Dispatchers.IO).collect{
                Log.d(TAG,it.toString())
            }
        }
    }
}