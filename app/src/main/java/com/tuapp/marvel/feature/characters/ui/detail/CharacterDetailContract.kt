package com.tuapp.marvel.feature.characters.ui.detail

import com.tuapp.marvel.core.ui.base.UiResult
import com.tuapp.marvel.feature.characters.domain.model.CharacterDetail

object CharacterDetailContract {
    data class CharacterDetailUiState(
        val detailResult: UiResult<CharacterDetail> = UiResult.Idle
    ) {
        fun toggleFavorite(): CharacterDetailUiState {
            val current = detailResult.getOrNull()
                ?: return this
            return copy(
                detailResult = UiResult.Success(current.copy(isFavorite = !current.isFavorite))
            )
        }
    }

    sealed class CharacterDetailUiIntent() {
        object ToggleFavorite: CharacterDetailUiIntent()
    }

    sealed class CharacterDetailEffect {
        data object UpdateFavoriteFailed: CharacterDetailEffect()
    }
}