package com.example.secondbeststoryever.data.dto

import com.google.gson.annotations.SerializedName

data class MangaPicturesResponse(
    val data: List<MangaPictureDto>
)


data class MangaPictureDto(
    val jpg: ImagePicsDto,
    val webp: ImageDto
)

data class ImagePicsDto(
    @SerializedName("image_url")
    val imageUrl: String
)