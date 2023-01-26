package com.joaquinco.marvelapp.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.joaquinco.marvelapp.R
import com.joaquinco.marvelapp.domain.MarvelCharacter

val charactersDummyList = listOf(
    MarvelCharacter(
        "id",
        "3-D Man",
        "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/landscape_xlarge.jpg"
    ),
    MarvelCharacter(
        "id",
        "3-D Man",
        "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/landscape_xlarge.jpg"
    )
)

@Preview(showBackground = true)
@Composable
fun CharactersGrid(characters: List<MarvelCharacter> = charactersDummyList, setFavorite:(MarvelCharacter)->Unit= {}, goDetail: (String, String) -> Unit = {_,_->}) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(characters) { character ->
            ItemCharacter(character.name, character.photo, character.isFavorite,{ favorite ->
                setFavorite(MarvelCharacter(character.id,character.name, character.photo, favorite))
            }){
                goDetail(character.id, character.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemCharacter(
    hero: String = "3-D Man",
    photo: String = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/landscape_xlarge.jpg",
    isFavorite: Boolean = false,
    setFavorite: (Boolean) -> Unit = {},
    goDetail: () -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable { goDetail() }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(if (isFavorite) Color.Yellow else Color.Cyan)
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

        Box(
            modifier = Modifier
                .background(Color.White.copy(0.8f))
                .padding(10.dp, 15.dp, 15.dp, 10.dp)
                .align(Alignment.TopEnd)
        ) {
            FavoritesIcon(isFavorite) {
                setFavorite(!isFavorite)
                Log.d("En Item", isFavorite.toString())

            }
        }
    }

}

@Composable
fun FavoritesIcon(isFavorite: Boolean = false, onClick: () -> Unit) {

    Image(
        painter = painterResource(
            id =
            if (isFavorite) {
                R.drawable.red_heart
            } else {
                R.drawable.white_heart
            }
        ),
        contentDescription = "Favorite icon",
        modifier = Modifier
            .size(30.dp)
            .clickable {
                onClick()
                Log.d("En Imagen", isFavorite.toString())
            }
    )
}

