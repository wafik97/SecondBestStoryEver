package com.example.secondbeststoryever.data.remote.mapper

import com.example.secondbeststoryever.data.dto.MangaLoreDto
import com.example.secondbeststoryever.data.model.MangaLore

fun MangaLoreDto.toLore(): MangaLore {
    return MangaLore(
        title = titleEnglish ?: title,
        japaneseTitle = titleJapanese,
        imageUrl = images.jpg?.imageUrl ?: "",

        synopsis = synopsis ?: "No synopsis available.",
        background = background ?: "No background information.",
        authors = authors?.joinToString(", ") { it.name } ?: "Unknown"
    )
}
