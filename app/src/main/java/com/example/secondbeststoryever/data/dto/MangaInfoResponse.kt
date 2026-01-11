package com.example.secondbeststoryever.data.dto

import com.google.gson.annotations.SerializedName

data class MangaInfoResponse(
    val data: MangaLoreDto
)


data class MangaLoreDto(
    @SerializedName("mal_id")
    val id: Int,

    val title: String,

    @SerializedName("title_english")
    val titleEnglish: String?,

    @SerializedName("title_japanese")
    val titleJapanese: String?,

    val synopsis: String?,
    val background: String?,

    val authors: List<AuthorDto>?,

    val images: MangaImagesDto
)


data class MangaImagesDto(
    val jpg: ImageInfoDto?,

)

data class ImageInfoDto(
    @SerializedName("large_image_url")
    val imageUrl: String?
)

data class AuthorDto(
    val name: String
)
