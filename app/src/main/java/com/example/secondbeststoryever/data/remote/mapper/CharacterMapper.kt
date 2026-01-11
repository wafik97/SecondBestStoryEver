package com.example.secondbeststoryever.data.remote.mapper

import com.example.secondbeststoryever.data.dto.CharacterInfoDto
import com.example.secondbeststoryever.data.dto.CharacterItemDto
import com.example.secondbeststoryever.data.model.Character
import com.example.secondbeststoryever.data.model.CharacterInfo

fun CharacterItemDto.toCharacter(): Character {
    return Character(
        id = character.id,
        name = character.name,
        imageUrl = character.images.jpg?.imageUrl?: character.images.webp?.imageUrl?: "",
        role = role
    )
}


fun CharacterInfoDto.toCharacter(): CharacterInfo {
    return CharacterInfo(
        name = name,
        nameKanji = nameKanji,
        nicknames = nicknames,
        imageUrl = images.jpg.imageUrl,
        about = about
    )
}