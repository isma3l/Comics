package com.tuapp.marvel.feature.characters.ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tuapp.marvel.feature.characters.ui.components.CharacterListContent
import com.tuapp.marvel.feature.characters.ui.common.CharacterListContract.CharacterListIntent

@Composable
fun FavoritesScreen(
    onComicClick: (Int) -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CharacterListContent(
        searchQuery = uiState.query,
        onSearch = { viewModel.onIntent(CharacterListIntent.SearchCharacters)},
        onQueryChange = { viewModel.onIntent(CharacterListIntent.UpdateSearchQuery(it)) },
        onToggleFavorite = { viewModel.onIntent(CharacterListIntent.ToggleFavorites(it)) },
        onCharacterClick = onComicClick,
        isLoading = uiState.characterResult.isLoading,
        error = uiState.characterResult.errorOrNull(),
        characters = uiState.characters
    )
}