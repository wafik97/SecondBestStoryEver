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
import com.example.secondbeststoryever.ui.theme.Dimens

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
                    modifier = Modifier.padding(Dimens.mediumSpacing)
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
fun CharacterContent(
    character: CharacterInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimens.mediumSpacing)
    ) {

        AsyncImage(
            model = character.imageUrl,
            contentDescription = character.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.infoPosterImageSize),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(Dimens.heightSpacing))

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
            Spacer(modifier = Modifier.height(Dimens.heightSpacing))
            Text(
                text = "Nicknames: ${character.nicknames.joinToString()}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(Dimens.heightSpacing))

        character.about?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
