package com.tuapp.marvel.feature.characters.ui.favorites

import androidx.lifecycle.viewModelScope
import com.tuapp.marvel.core.extensions.asUiResult
import com.tuapp.marvel.core.ui.base.BaseViewModel
import com.tuapp.marvel.feature.characters.domain.model.Character
import com.tuapp.marvel.feature.characters.domain.usecase.GetFavoritesUseCase
import com.tuapp.marvel.feature.characters.domain.usecase.ToggleFavoriteUseCase
import com.tuapp.marvel.feature.characters.ui.common.CharacterListContract.CharacterListState
import com.tuapp.marvel.feature.characters.ui.common.CharacterListContract.CharacterListEffect
import com.tuapp.marvel.feature.characters.ui.common.CharacterListContract.CharacterListIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): BaseViewModel<CharacterListState, CharacterListEffect, CharacterListIntent>(CharacterListState()) {
    private val queryFlow = MutableStateFlow("")

    init {
        observeFavorites()
    }

    override fun onIntent(intent: CharacterListIntent) {
        when (intent) {
            is CharacterListIntent.UpdateSearchQuery -> { updateState { copy(query = intent.query) } }
            is CharacterListIntent.SearchCharacters -> { queryFlow.value = uiState.value.query }
            is CharacterListIntent.ToggleFavorites -> { onToggleFavorite(intent.character)}
        }
    }

    private fun observeFavorites() {
        queryFlow
            .flatMapLatest { query ->
                getFavoritesUseCase(query)
                    .asUiResult()
            }
            .onEach { updateState { copy(characterResult = it) } }
            .launchIn(viewModelScope)
    }

    private fun onToggleFavorite(character: Character) {
        viewModelScope.launch {
            toggleFavoriteUseCase(character)
                .onFailure {
                    sendEffect(CharacterListEffect.UpdateFavoriteFailed)
                }
        }
    }
}
