package com.joaquinco.marvelapp.ui.characterlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterListScreen(viewModel: CharacterListViewModel = hiltViewModel()){
    Scaffold(modifier = Modifier.fillMaxSize()) {

    }
}