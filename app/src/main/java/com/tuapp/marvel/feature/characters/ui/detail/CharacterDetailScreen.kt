package com.tuapp.marvel.feature.characters.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.tuapp.marvel.core.ui.components.FavoriteIcon

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val detail = uiState.characterDetail

    when {
        uiState.isLoading -> CircularProgressIndicator()
        uiState.error != null -> Text(text = uiState.error!!)
        detail != null -> Column {
            Box {
                AsyncImage(
                    model = detail.imageUrl,
                    contentDescription = detail.name,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
                FavoriteIcon(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onToggleFavorite = { viewModel.onIntent(CharacterDetailUiIntent.ToggleFavorite) },
                    isFavorite = detail.isFavorite,
                    size = 36.dp
                )
            }
            Text(text = detail.description ?: "", Modifier.padding(16.dp))
        }
    }

}