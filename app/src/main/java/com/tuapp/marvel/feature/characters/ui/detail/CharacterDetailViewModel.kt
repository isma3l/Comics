package com.tuapp.marvel.feature.characters.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.tuapp.marvel.app.navigation.AppDestinations
import com.tuapp.marvel.feature.characters.domain.usecase.GetCharacterDetailUseCase
import com.tuapp.marvel.feature.characters.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): ViewModel() {

    private val _characterId: Int = checkNotNull(
        savedStateHandle.toRoute<AppDestinations.CharacterDetail>().characterId
    )

    private val _uiState = MutableStateFlow(CharacterDetailUiState())
    val uiState: StateFlow<CharacterDetailUiState> = _uiState.asStateFlow()

    init {
        loadCharacterDetails()
    }

    fun onIntent(intent: CharacterDetailUiIntent) {
        when (intent) {
            is CharacterDetailUiIntent.ToggleFavorite -> updateFavorite()
        }
    }

    private fun loadCharacterDetails() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getCharacterDetailUseCase(_characterId)
                .onSuccess { characterDetail ->
                    _uiState.update { it.copy(characterDetail = characterDetail, isLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(error = error.message, isLoading = false) }
                }
        }
    }

    private fun updateFavorite() {
        val character = _uiState.value.characterDetail?.toCharacter() ?: return
        viewModelScope.launch {
            toggleFavoriteUseCase.invoke(character)
                .onSuccess {
                    _uiState.update { currentState ->
                        val currentDetail = currentState.characterDetail
                        currentState.copy(
                            characterDetail = currentDetail?.copy(
                                isFavorite = !currentDetail.isFavorite
                            )
                        )
                    }
                }
                .onFailure { error ->
                    // _events.send(UiEvent.ShowError("No se pudo actualizar favorito"))
                    // analytics.trackError("fav_error", e.message)
                }
        }
    }
}