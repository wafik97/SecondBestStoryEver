package com.example.secondbeststoryever.ui.screens.lore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.secondbeststoryever.data.model.MangaLore
import com.example.secondbeststoryever.ui.theme.Dimens


@Composable
fun LoreScreen(
    mangaId: Int = 132129,
    viewModel: LoreViewModel = viewModel()
) {
    LaunchedEffect(Unit) {  // and this don't forget
        viewModel.loadLore(mangaId)
    }

    val lore = viewModel.lore.value

    when {
        viewModel.isLoading.value -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        lore != null -> {
            LoreContent(lore)
        }
    }
}

@Composable
fun LoreContent(lore: MangaLore) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.mediumSpacing),
        verticalArrangement = Arrangement.spacedBy(Dimens.mediumSpacing)
    ) {

        item {
            AsyncImage(
                model = lore.imageUrl,
                contentDescription = lore.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.bigPosterImageSize),
                contentScale = ContentScale.Crop
            )
        }

        item {
            Text(
                text = lore.title,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        lore.japaneseTitle?.let {
            item {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {
            ExpandableSection(
                title = "Synopsis",
                content = lore.synopsis,
                toExpand = true
            )
        }

        item {
            ExpandableSection(
                title = "Background",
                content = lore.background,
                toExpand = false
            )
        }

        item {
            Text(
                text = "Authors: ${lore.authors}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ExpandableSection(
    title: String,
    content: String,
    toExpand : Boolean
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.clickable { expanded = !expanded }
        )

        Text(
            text = content,
            maxLines = if (expanded) Int.MAX_VALUE else 5,
            overflow = TextOverflow.Ellipsis
        )

        if(toExpand) {
            Text(
                text = if (expanded) "Show less" else "Read more",
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable { expanded = !expanded },
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


