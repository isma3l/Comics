package com.tuapp.marvel.feature.characters.ui.characterList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tuapp.marvel.core.ui.components.CharacterListContent

@Composable
fun CharacterListScreen(
    onComicClick: (Int) -> Unit,
    viewModel: ComicListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CharacterListContent(
        searchQuery = uiState.searchQuery,
        onSearch = { viewModel.onIntent(CharacterListUiIntent.SearchCharacters)},
        onQueryChange = { viewModel.onIntent(CharacterListUiIntent.UpdateSearchQuery(it)) },
        onToggleFavorite = { viewModel.onIntent(CharacterListUiIntent.ToggleFavorite(it)) },
        onCharacterClick = onComicClick,
        isLoading = uiState.isLoading,
        error = uiState.error,
        characters = uiState.characters
    )
}