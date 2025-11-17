package com.tuapp.marvel.feature.characters.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuapp.marvel.feature.characters.domain.model.Character
import com.tuapp.marvel.feature.characters.domain.usecase.GetFavoritesUseCase
import com.tuapp.marvel.feature.characters.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    private val _activeQuery: MutableStateFlow<String?> = MutableStateFlow(null)

    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    init {
        _activeQuery.flatMapLatest { query ->
            getFavoritesUseCase(query)
                .onStart {
                    _uiState.update { it.copy(isLoading = true) }
                }
                .catch { error ->
                    _uiState.update { it.copy(error = error.message, isLoading = false) }
                }
        }.onEach { characters ->
            _uiState.update { it.copy(isLoading = false, favorites = characters) }
        }.launchIn(viewModelScope)
    }

    fun onIntent(intent: FavoriteUiIntent) {
        when (intent) {
            is FavoriteUiIntent.SearchFavorites -> {
                _activeQuery.value = _uiState.value.searchQuery
            }
            is FavoriteUiIntent.UpdateSearchQuery -> {
                _uiState.update { it.copy(searchQuery = intent.query) }
            }
            is FavoriteUiIntent.ToggleFavorite -> toggleFavorite(intent.character)
        }
    }

    private fun toggleFavorite(character: Character) {
        viewModelScope.launch {
            toggleFavoriteUseCase(character)
                .onFailure { error ->
                    // _events.send(UiEvent.ShowError("No se pudo actualizar favorito"))
                    // analytics.trackError("fav_error", e.message)
                }
        }
    }
}