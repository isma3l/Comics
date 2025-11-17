package com.tuapp.marvel.feature.characters.ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tuapp.marvel.core.ui.components.CharacterListContent

@Composable
fun FavoritesScreen(
    onComicClick: (Int) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CharacterListContent(
        searchQuery = uiState.searchQuery,
        onSearch = { viewModel.onIntent(FavoriteUiIntent.SearchFavorites)},
        onQueryChange = { viewModel.onIntent(FavoriteUiIntent.UpdateSearchQuery(it)) },
        onToggleFavorite = { viewModel.onIntent(FavoriteUiIntent.ToggleFavorite(it)) },
        onCharacterClick = onComicClick,
        isLoading = uiState.isLoading,
        error = uiState.error,
        characters = uiState.favorites
    )
}