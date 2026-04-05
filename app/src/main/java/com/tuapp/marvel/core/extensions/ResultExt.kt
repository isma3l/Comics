package com.tuapp.marvel.core.extensions

import com.tuapp.marvel.core.ui.base.UiResult

fun <T> Result<T>.toUiResult(): UiResult<T> = fold(
    onSuccess = { UiResult.Success(it) },
    onFailure = { UiResult.Error(it.message ?: "Error") }
)