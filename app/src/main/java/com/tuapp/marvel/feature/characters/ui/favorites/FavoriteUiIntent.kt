package com.tuapp.marvel.feature.characters.ui.favorites

import com.tuapp.marvel.feature.characters.domain.model.Character

sealed class FavoriteUiIntent {
    data class UpdateSearchQuery(val query: String): FavoriteUiIntent()
    object SearchFavorites: FavoriteUiIntent()
    data class ToggleFavorite(val character: Character): FavoriteUiIntent()
}