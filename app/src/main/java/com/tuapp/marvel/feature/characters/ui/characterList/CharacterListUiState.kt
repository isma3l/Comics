package com.tuapp.marvel.feature.characters.ui.characterList

import com.tuapp.marvel.feature.characters.domain.model.Character

data class CharacterListUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null,
    val searchQuery: String = ""
)