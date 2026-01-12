package com.example.secondbeststoryever.data.model

import com.example.secondbeststoryever.data.dto.MangaPictureDto

data class MangaPicture(
    val imageUrl: String
)


fun MangaPictureDto.toPicture(): MangaPicture {
    return MangaPicture(
        imageUrl = jpg.imageUrl // choose JPG for simplicity
    )
}
