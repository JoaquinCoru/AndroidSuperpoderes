package com.joaquinco.marvelapp.ui.detailList

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            val series = viewModel.series.collectAsState()

            if (series.value.isEmpty()) {
                ProgressLayout()
            }else {
                Text(
                    "Series",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp)
                )
                DetailGrid(series.value)


            }
        }


    }


}