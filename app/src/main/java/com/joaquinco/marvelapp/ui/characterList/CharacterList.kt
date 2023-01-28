package com.joaquinco.marvelapp.ui.characterList

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joaquinco.marvelapp.ui.components.CharactersGrid

@Preview(showBackground = true)
@Composable
fun CharacterListScreen(viewModel: CharacterListViewModel = hiltViewModel(), onCharacterClick: (String, String)-> Unit = {_,_ ->}) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Marvel Characters")
                }
            )
        }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            val characters = viewModel.characters.collectAsState()

            if (characters.value.isEmpty()) {
                ProgressLayout()
            } else {
                CharactersGrid(characters.value,{ mvcharacter ->
                    viewModel.setLike(mvcharacter)
                    Log.d("Character List", mvcharacter.isFavorite.toString())
                }){ characterId, characterName ->
                    onCharacterClick(characterId, characterName)
                }
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