package com.tuapp.marvel.feature.characters.ui.characterList

import com.tuapp.marvel.feature.characters.domain.model.Character

sealed class CharacterListUiIntent {
    data class UpdateSearchQuery(val query: String): CharacterListUiIntent()
    object  SearchCharacters: CharacterListUiIntent()
    data class ToggleFavorite(val character: Character): CharacterListUiIntent()
}