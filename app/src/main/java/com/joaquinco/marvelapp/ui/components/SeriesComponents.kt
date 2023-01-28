package com.joaquinco.marvelapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.joaquinco.marvelapp.domain.MarvelSerie


@Preview(showBackground = true)
@Composable
fun DetailGrid(series: List<MarvelSerie> = emptyList()) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        item(span = { GridItemSpan(2) }) {
            Title(text = "Series")
        }

        items(series) { serie ->
            ItemSerie(serie.title, serie.photo)
        }

        item(span = { GridItemSpan(2) }) {
            Title(text = "Comics")
        }

    }
}

@Composable
fun Title(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(5.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun ItemSerie(
    title: String = "Ant-Man & the Wasp (2010 - 2011)",
    photo: String = "http://i.annihil.us/u/prod/marvel/i/mg/3/60/4c606835416be/landscape_xlarge.jpg"
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.Cyan)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = photo),
            contentDescription = "Compose icon",
            modifier = Modifier
                .size(150.dp)
                .padding(0.dp, 5.dp)
        )
        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            style = MaterialTheme.typography.h6.copy(fontSize = 20.sp)
        )
    }
}