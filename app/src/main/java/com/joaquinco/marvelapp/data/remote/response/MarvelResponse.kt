package com.joaquinco.marvelapp.data.remote


data class MarvelResponse (
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: DataMarvel
)

data class DataMarvel (
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<Result>
)

data class Result (
    val id: Int,
    val name: String?, //nullable para characters
    val title: String?, //nullable para series
    val description: String?, // Puede o no venir a vacio
    val thumbnail: Thumbnail,
    val resourceURI: String? // Puede o no venir a vacio
)

data class Thumbnail (
    val path: String,
    val extension: String
){
    fun getImageUrl(type: PhotoType = PhotoType.landscape): String {
        return "$path/${type.value}.${extension}"
    }
}

enum class PhotoType(val value:String) {
    portrait("portrait_xlarge"),
    landscape("landscape_xlarge")
}


/*enum class Extension{
    GIF,
    JPG
}*/

/*class ExtensionAdapter {
    @ToJson fun toJson(extension: Extension): String {
        return  extension.name
    }

    @FromJson fun fromJson(extension: String): Extension {
        return when (extension) {
            "jpg" -> Extension.JPG
            "gif" -> Extension.GIF
            else -> throw JsonDataException("Unknown extension: $extension")
        }
    }
}*/

