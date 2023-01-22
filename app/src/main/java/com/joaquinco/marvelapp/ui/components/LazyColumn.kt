package com.joaquinco.marvelapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.joaquinco.marvelapp.domain.MarvelCharacter

@Preview(showBackground = true)
@Composable
fun MyLazyGrid(characters: List<MarvelCharacter> = emptyList()) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()) {
        items(characters) {
            Item(it.name, it.photo)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyLazyColumn(characters: List<MarvelCharacter> = emptyList()) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(characters) { character ->
            Item(character.name, character.photo)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Item(
    hero: String = "3-D Man",
    photo: String = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/landscape_xlarge.jpg"
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.Cyan)
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = photo),
            contentDescription = "Compose icon",
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = hero,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            style = MaterialTheme.typography.h5.copy(fontSize = 20.sp)
        )
    }
}