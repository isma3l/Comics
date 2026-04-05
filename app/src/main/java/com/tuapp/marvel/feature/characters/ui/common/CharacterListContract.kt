package com.tuapp.marvel.feature.characters.ui.common

import com.tuapp.marvel.core.ui.base.UiResult
import com.tuapp.marvel.feature.characters.domain.model.Character

object CharacterListContract {
    data class CharacterListState(
        val characterResult: UiResult<List<Character>> = UiResult.Idle,
        val query: String = ""
    ) {
        val characters get() = characterResult.getOrNull() ?: emptyList()
    }

    sealed class CharacterListIntent {
        data class UpdateSearchQuery(val query: String): CharacterListIntent()
        data object SearchCharacters: CharacterListIntent()
        data class ToggleFavorites(val character: Character): CharacterListIntent()
    }

    sealed class CharacterListEffect {
        data object UpdateFavoriteFailed: CharacterListEffect()
    }
}