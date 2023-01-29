package com.joaquinco.marvelapp.ui.characterList

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joaquinco.marvelapp.ui.components.CharactersGrid

/*
 Como nuevos componentes de compose se ha incluido el CircularProgressIndicator que nos permite mostrar un círculo
 de indicador de progreso animado al que se le pueden customizar varios valores como stroke, width, valor, etc:
 https://www.jetpackcompose.net/jetpack-compose-progress-indicator-progressbar

 Aparte se ha incluido el TopAppBar que es una de las propiedades que nos permite incluir el Scaffold
 Con ella podemos mostrara la barra con el título en cada ventana y otros aspectos como botones de opciones y de navegación
 con funcionalidad como se puede ver en la vista de detalle
 https://www.develou.com/topappbar-en-jetpack-compose/
 */

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
            strokeWidth = 5.dp,
            modifier = Modifier
                .fillMaxHeight()
                .width(75.dp)
                .weight(2f)
        )
    }

}