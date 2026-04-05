package com.tuapp.marvel.core.extensions

import com.tuapp.marvel.core.ui.base.UiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.asUiResult() = this
    .map<T, UiResult<T>> { UiResult.Success(it) }
    .onStart { emit(UiResult.Loading) }
    .catch { emit(UiResult.Error(it.message)) }