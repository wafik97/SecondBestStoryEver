package com.example.secondbeststoryever.ui.screens.characters


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.secondbeststoryever.data.model.Character
import com.example.secondbeststoryever.data.remote.RetrofitInstance

@Composable
fun CharacterDetailsScreen(characterId: Int) {
 /*   val characterState = remember { mutableStateOf<Character?>(null) }

    LaunchedEffect(characterId) {
        try {
            val response = RetrofitInstance.api.getCharacter(characterId)
            characterState.value = response.data.toCharacter()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            characterState.value?.let { character ->
                AsyncImage(
                    model = character.imageUrl,
                    contentDescription = character.name,
                    modifier = Modifier.fillMaxWidth().height(250.dp),
                    contentScale = ContentScale.Crop
                )
                Text(text = character.name, style = MaterialTheme.typography.headlineMedium)
                Text(text = character.role, style = MaterialTheme.typography.bodyMedium)
                Text(text = character.about, style = MaterialTheme.typography.bodySmall)
            } ?: Text("Loading...")
        }
    }*/
}
