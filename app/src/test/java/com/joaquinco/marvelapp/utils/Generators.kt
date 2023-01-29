package com.joaquinco.marvelapp.utils

import com.joaquinco.marvelapp.data.local.model.MVCharacterLocal
import com.joaquinco.marvelapp.data.remote.DataMarvel
import com.joaquinco.marvelapp.data.remote.MarvelResponse
import com.joaquinco.marvelapp.data.remote.Result
import com.joaquinco.marvelapp.data.remote.Thumbnail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


fun generateLocalCharactersFlow(): Flow<List<MVCharacterLocal>> {
    return flow {
        emit(
            listOf(MVCharacterLocal(
                "id",
                "name",
                "photo",
                false
            ))
        )
    }
}

fun generateFakeMarvelResponse(): MarvelResponse {
    return MarvelResponse(
        200,
        "OK",
        "© 2023 MARVEL",
        "Data provided by Marvel. © 2023 MARVEL",
        "",
        "",
        DataMarvel(
            0,
            20,
            20,
            20,
            listOf(
                Result(
                1,
                "name",
                "title",
                "description",
                Thumbnail(
                    "path",
                    "extension"
                ),
                "resourceUri"
            )
            )
        )
    )
}