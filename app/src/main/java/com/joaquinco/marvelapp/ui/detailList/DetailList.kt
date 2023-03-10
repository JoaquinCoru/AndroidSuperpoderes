package com.joaquinco.marvelapp.ui.detailList

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joaquinco.marvelapp.ui.characterList.ProgressLayout
import com.joaquinco.marvelapp.ui.components.DetailGrid


@Preview(showBackground = true)
@Composable
fun DetailListScreen(
    id: String = "1",
    name: String = "World",
    goBack: () -> Unit = {},
    viewModel: DetailViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit, block = {
        val idNumber = id.toInt()
        Log.d("Vista", "LLamando get series")
        viewModel.getSeries(idNumber)
        viewModel.getComics(idNumber)
    })

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = name)
                },
                navigationIcon = {
                    IconButton(onClick = { goBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Ir hacia atrás"
                        )
                    }
                }
            )
        }
    ) {
        val isLoading = viewModel.isLoading.collectAsState()
        val series = viewModel.series.collectAsState()
        val comics = viewModel.comics.collectAsState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {

            if (isLoading.value) {

                ProgressLayout()

            } else {
                if (series.value.isEmpty() && comics.value.isEmpty() ){
                    Text(text = "No hay resultados") //P. ej. Aginar, que no tiene
                }else{
                    DetailGrid(series.value, comics.value)
                }

            }
        }


    }


}