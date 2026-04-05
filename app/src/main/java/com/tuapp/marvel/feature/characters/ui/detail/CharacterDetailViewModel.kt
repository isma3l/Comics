package com.tuapp.marvel.feature.characters.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.tuapp.marvel.app.navigation.AppDestinations
import com.tuapp.marvel.core.extensions.toUiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.tuapp.marvel.core.ui.base.BaseViewModel
import com.tuapp.marvel.core.ui.base.UiResult
import com.tuapp.marvel.feature.characters.domain.usecase.GetCharacterDetailUseCase
import com.tuapp.marvel.feature.characters.domain.usecase.ToggleFavoriteUseCase
import com.tuapp.marvel.feature.characters.ui.detail.CharacterDetailContract.CharacterDetailUiState
import com.tuapp.marvel.feature.characters.ui.detail.CharacterDetailContract.CharacterDetailUiIntent
import com.tuapp.marvel.feature.characters.ui.detail.CharacterDetailContract.CharacterDetailEffect
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): BaseViewModel<CharacterDetailUiState, CharacterDetailEffect, CharacterDetailUiIntent>(
    CharacterDetailUiState()
) {
    private val _characterId: Int = checkNotNull(
        savedStateHandle.toRoute<AppDestinations.CharacterDetail>().characterId
    )

    init {
        fetchDetails(_characterId)
    }

    override fun onIntent(intent: CharacterDetailUiIntent) {
        when (intent) {
            is CharacterDetailUiIntent.ToggleFavorite -> onToggleFavorite()
        }
    }

    private fun fetchDetails(characterId: Int) {
        viewModelScope.launch {
            updateState { copy(detailResult = UiResult.Loading) }

            val uiResult = getCharacterDetailUseCase(_characterId).toUiResult()
            updateState { copy(detailResult = uiResult) }
        }
    }

    private fun onToggleFavorite() {
        val character = uiState.value.detailResult.getOrNull()?.toCharacter() ?: return
        viewModelScope.launch {
            toggleFavoriteUseCase(character)
                .onSuccess {
                    updateState { toggleFavorite() }
                }
                .onFailure {
                    sendEffect(CharacterDetailEffect.UpdateFavoriteFailed)
                }
        }
    }
}