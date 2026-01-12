package com.example.secondbeststoryever.ui.screens.pictures

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondbeststoryever.data.model.MangaPicture
import com.example.secondbeststoryever.data.model.toPicture
import com.example.secondbeststoryever.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class PicturesViewModel : ViewModel() {

    val pictures = mutableStateOf<List<MangaPicture>>(emptyList())
    val isLoading = mutableStateOf(false)

    fun loadPictures(mangaId: Int) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = RetrofitInstance.api.getMangaPictures(mangaId)
                pictures.value = response.data.map { it.toPicture() }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }
        }
    }
}
