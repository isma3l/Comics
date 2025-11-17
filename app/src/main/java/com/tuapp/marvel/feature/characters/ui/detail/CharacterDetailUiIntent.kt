package com.tuapp.marvel.feature.characters.ui.detail

import com.tuapp.marvel.feature.characters.domain.model.Character

sealed class CharacterDetailUiIntent() {
    object ToggleFavorite: CharacterDetailUiIntent()
}