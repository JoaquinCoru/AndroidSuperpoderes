package com.joaquinco.marvelapp.ui.detailList

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Preview(showBackground = true)
@Composable
fun DetailListScreen(id:String=" World") {

    Text(text ="Hello $id" )
}