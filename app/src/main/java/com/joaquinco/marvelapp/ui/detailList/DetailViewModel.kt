package com.joaquinco.marvelapp.ui.detailList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaquinco.marvelapp.domain.MarvelSerie
import com.joaquinco.marvelapp.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _series = MutableStateFlow(emptyList<MarvelSerie>())
    val series:StateFlow<List<MarvelSerie>> get() = _series

    private val _comics = MutableStateFlow(emptyList<MarvelSerie>())
    val comics:StateFlow<List<MarvelSerie>> get() = _comics

    fun getSeries(characterId: Int) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSeries(characterId).flowOn(Dispatchers.IO).collect{
                Log.d("Series",it.toString())
                _series.value = it
                _isLoading.value = false
            }
        }
    }

    fun getComics(characterId: Int) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            repository.getComics(characterId).flowOn(Dispatchers.IO).collect{
                Log.d("Comics", it.toString())
                _comics.value = it
                _isLoading.value = false
            }
        }
    }
}