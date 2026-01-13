package com.example.secondbeststoryever.ui.screens.pictures

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.secondbeststoryever.ui.theme.Dimens

@Composable
fun PicturesScreen(
    mangaId: Int = 132129,
    viewModel: PicturesViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadPictures(mangaId)
    }

    val pictures = viewModel.pictures.value
    val isLoading = viewModel.isLoading.value

    if (isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.smallSpacing),
            verticalArrangement = Arrangement.spacedBy(Dimens.smallSpacing),
            horizontalArrangement = Arrangement.spacedBy(Dimens.smallSpacing)
        ) {
            items(pictures) { picture ->
                AsyncImage(
                    model = picture.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.posterImageSize),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
