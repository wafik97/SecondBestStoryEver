package com.example.secondbeststoryever.ui.screens.characters


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.secondbeststoryever.data.model.Character
import com.example.secondbeststoryever.data.remote.RetrofitInstance
import com.example.secondbeststoryever.ui.theme.Gray
import com.example.secondbeststoryever.ui.theme.SecondBestStoryEverTheme
import com.example.secondbeststoryever.ui.theme.Dimens



@Composable
fun CharactersScreen(
    navController: NavController,
    mangaId: Int = 132129,
    viewModel: CharactersViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.loadCharacters(mangaId)
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Text(
                text = "Population of the Manga",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(Dimens.screenPadding)
            )

            OutlinedTextField(
                value = viewModel.searchQuery.value,
                onValueChange = { viewModel.searchQuery.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.screenPadding),
                placeholder = { Text("Search character...") },
                singleLine = true
            )

            Text(
                text = if (viewModel.isGrid.value) "Grid View" else "List View",
                modifier = Modifier
                    .padding(Dimens.screenPadding)
                    .align(Alignment.Start)
                    .clickable { viewModel.toggleLayout() },
                style = MaterialTheme.typography.labelLarge
            )

            val characters = viewModel.filteredCharacters()

            if (viewModel.isGrid.value) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimens.screenPadding)
                ) {
                    items(characters) { character ->
                        CharacterGridItem(character) {
                            navController.navigate("character/${character.id}")
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimens.mediumSpacing)
                ) {
                    items(characters) { character ->
                        CharacterListItem(character) {
                            navController.navigate("character/${character.id}")
                        }
                    }
                }
            }
        }
    }
}

/*
@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = character.name)
        }
    }
}*/


@Composable
fun CharacterListItem(
    character: Character,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = Dimens.listItemPadding)
            .fillMaxWidth()
            .height(Dimens.listItemHeight)
            .clickable { onClick() },


    ) {
        Row(


            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.listItemPadding),
                    verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                modifier = Modifier.size(Dimens.listImageSize),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(start = Dimens.listTextSpacing)) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = character.role,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


@Composable
fun CharacterGridItem(
    character: Character,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(Dimens.gridItemPadding)
            .fillMaxWidth()
            .height(Dimens.gridItemHeight)
            .clickable { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.gridImageHeight),
                contentScale = ContentScale.Crop
            )
            Text(
                text = character.name,
                Modifier.padding(Dimens.gridItemPadding),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SecondBestStoryEverTheme {
        val navController = rememberNavController()
        CharactersScreen(navController)
    }
}

