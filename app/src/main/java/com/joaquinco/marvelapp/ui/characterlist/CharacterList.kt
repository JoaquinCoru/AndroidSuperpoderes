package com.joaquinco.marvelapp.ui.characterlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.joaquinco.marvelapp.ui.components.MyLazyColumn
import com.joaquinco.marvelapp.ui.components.MyLazyGrid

@Composable
fun CharacterListScreen(viewModel: CharacterListViewModel = hiltViewModel()){
    Scaffold(modifier = Modifier.fillMaxSize()) {

        val characters = viewModel.characters.collectAsState()
        MyLazyGrid(characters.value)
    }
}