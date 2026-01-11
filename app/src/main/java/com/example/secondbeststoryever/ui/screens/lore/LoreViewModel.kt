package com.example.secondbeststoryever.ui.screens.lore

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondbeststoryever.data.model.MangaLore
import com.example.secondbeststoryever.data.remote.RetrofitInstance
import com.example.secondbeststoryever.data.remote.mapper.toLore
import kotlinx.coroutines.launch

class LoreViewModel : ViewModel() {

    val lore = mutableStateOf<MangaLore?>(null)
    val isLoading = mutableStateOf(false)

    fun loadLore(mangaId: Int) {
        viewModelScope.launch {   // check this pls
            isLoading.value = true
            try {
                lore.value = RetrofitInstance.api
                    .getMangaInfo(mangaId)
                    .data
                    .toLore()
            } finally {
                isLoading.value = false
            }
        }
    }
}
