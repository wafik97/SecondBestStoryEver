package com.example.secondbeststoryever.data.dto

import com.example.secondbeststoryever.data.model.Character
import com.google.gson.annotations.SerializedName


data class CharactersResponse(
    val data: List<CharacterItemDto>
)

/*
data class CharacterResponse(
    val characterItemDto: CharacterItemDto,
    val about: String
)
*/

data class CharacterItemDto(
    val character: CharacterDto,
    val role: String
)

data class CharacterDto(
    @SerializedName("mal_id")
    val id: Int,
    val name: String,
    val url: String,
    val images: CharacterImagesDto
)


data class CharacterImagesDto(
    val jpg: ImageDto,
    val webp: ImageDto
)


data class ImageDto(
    @SerializedName("image_url")
    val imageUrl: String
)

fun CharacterItemDto.toCharacter(): com.example.secondbeststoryever.data.model.Character {
    return Character(
        id = character.id,
        name = character.name,
        imageUrl = character.images.jpg.imageUrl,
        role = role
    )
}