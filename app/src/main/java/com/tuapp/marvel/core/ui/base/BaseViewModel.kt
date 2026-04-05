package com.tuapp.marvel.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Effect, Intent>(initialState: State) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()
    private val _effect = Channel<Effect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    protected fun updateState(reducer: State.() -> State) {
        _uiState.update { it.reducer() }
    }

    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch { _effect.send(effect) }
    }

    abstract fun onIntent(intent: Intent)
}