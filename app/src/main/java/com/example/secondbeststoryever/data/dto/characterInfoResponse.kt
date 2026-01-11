package com.example.secondbeststoryever.data.dto

import com.google.gson.annotations.SerializedName

data class CharactersInfoResponse(
    @SerializedName("data")
    val character: CharacterInfoDto,

)

data class CharacterInfoDto(
    @SerializedName("mal_id")
    val id: Int,
    val name: String,
    @SerializedName("name_kanji")
    val nameKanji: String?,
    val nicknames: List<String>,
    val images: CharacterImages,
    val about: String?
)

data class CharacterImages(
    val jpg: ImageJpg
)

data class ImageJpg(
    @SerializedName("image_url")
    val imageUrl: String
)
