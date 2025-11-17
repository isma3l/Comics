package com.tuapp.marvel.feature.characters.ui.detail

import com.tuapp.marvel.feature.characters.domain.model.CharacterDetail

data class CharacterDetailUiState(
    val characterDetail: CharacterDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)