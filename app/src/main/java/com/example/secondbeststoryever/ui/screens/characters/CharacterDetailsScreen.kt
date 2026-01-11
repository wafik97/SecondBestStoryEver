package com.example.secondbeststoryever.ui.screens.characters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.secondbeststoryever.data.model.CharacterInfo
import com.example.secondbeststoryever.data.remote.RetrofitInstance
import com.example.secondbeststoryever.data.remote.mapper.toCharacter

@Composable
fun CharacterDetailsScreen(characterId: Int) {

    var character by remember { mutableStateOf<CharacterInfo?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(characterId) {
        try {
            val response = RetrofitInstance.api.getCharacter(characterId)
            character = response.character.toCharacter()
        } catch (e: Exception) {
            error = e.localizedMessage
        } finally {
            isLoading = false
        }
    }

    Scaffold { innerPadding ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Text(
                    text = error ?: "Unknown error",
                    modifier = Modifier.padding(16.dp)
                )
            }

            character != null -> {
                CharacterContent(
                    character = character!!,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}


@Composable
private fun CharacterContent(
    character: CharacterInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        AsyncImage(
            model = character.imageUrl,
            contentDescription = character.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = character.name,
            style = MaterialTheme.typography.headlineMedium
        )

        character.nameKanji?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (character.nicknames.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Nicknames: ${character.nicknames.joinToString()}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        character.about?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
