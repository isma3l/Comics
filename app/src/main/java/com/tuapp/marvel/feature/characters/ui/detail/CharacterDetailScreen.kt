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
import com.tuapp.marvel.core.ui.base.UiResult
import com.tuapp.marvel.core.ui.components.ErrorMessage
import com.tuapp.marvel.core.ui.components.FavoriteIcon
import com.tuapp.marvel.feature.characters.domain.model.CharacterDetail
import com.tuapp.marvel.feature.characters.ui.detail.CharacterDetailContract.CharacterDetailUiIntent

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val detail = uiState.detailResult) {
        is UiResult.Idle -> Unit
        is UiResult.Loading -> CircularProgressIndicator()
        is UiResult.Error -> ErrorMessage(detail.message)
        is UiResult.Success -> CharacterDetailContent(
            details = detail.data,
            onToggleFavorite = { viewModel.onIntent(CharacterDetailUiIntent.ToggleFavorite) }
        )
    }
}


@Composable
private fun CharacterDetailContent(
    details: CharacterDetail,
    onToggleFavorite: () -> Unit
) {
    Column {
        Box {
            AsyncImage(
                model = details.imageUrl,
                contentDescription = details.name,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            FavoriteIcon(
                modifier = Modifier.align(Alignment.TopEnd),
                onToggleFavorite = onToggleFavorite,
                isFavorite = details.isFavorite,
                size = 36.dp
            )
        }
        Text(text = details.description ?: "", Modifier.padding(16.dp))
    }
}
