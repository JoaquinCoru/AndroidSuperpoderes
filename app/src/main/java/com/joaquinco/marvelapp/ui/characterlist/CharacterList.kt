package com.joaquinco.marvelapp.ui.characterlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joaquinco.marvelapp.ui.components.CharactersGrid

@Composable
fun CharacterListScreen(viewModel: CharacterListViewModel = hiltViewModel()) {
    Scaffold(modifier = Modifier.fillMaxSize()) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                "Lista de personajes",
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp)
            )
            
            val characters = viewModel.characters.collectAsState()

            if (characters.value.isEmpty()) {
                ProgressLayout()
            } else {
                CharactersGrid(characters.value)
            }
        }

    }
}

@Composable
fun ProgressLayout(){

    Column() {
        Spacer(modifier = Modifier
            .fillMaxHeight()
            .weight(1f))
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxHeight()
                .width(75.dp)
                .weight(2f)
        )
    }

}