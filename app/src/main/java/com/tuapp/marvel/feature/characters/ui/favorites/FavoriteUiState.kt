package com.tuapp.marvel.feature.characters.ui.favorites

import com.tuapp.marvel.feature.characters.domain.model.Character

data class FavoriteUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val favorites: List<Character> = emptyList(),
    val searchQuery: String = ""
)