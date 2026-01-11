package com.example.secondbeststoryever.data.model

data class CharacterInfo(
    val name: String,
    val nameKanji: String?,
    val nicknames: List<String>,
    val imageUrl: String,
    val about: String?

)