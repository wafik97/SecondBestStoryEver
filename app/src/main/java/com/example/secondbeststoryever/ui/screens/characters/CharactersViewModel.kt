package com.example.secondbeststoryever.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondbeststoryever.data.model.Character
import com.example.secondbeststoryever.data.remote.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import com.example.secondbeststoryever.data.remote.mapper.toCharacter

class CharactersViewModel : ViewModel() {

    val characters = mutableStateOf<List<Character>>(emptyList())
    val searchQuery = mutableStateOf("")
    val isGrid = mutableStateOf(false)

    fun loadCharacters(mangaId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getMangaCharacters(mangaId)
                characters.value = response.data.map { it.toCharacter() }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



    fun filteredCharacters(): List<Character> {
        return characters.value.filter {
            it.name.contains(searchQuery.value, ignoreCase = true)
        }
    }

    fun toggleLayout() {
        isGrid.value = !isGrid.value
    }
}
