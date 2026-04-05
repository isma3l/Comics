package com.tuapp.marvel.core.ui.base

sealed class UiResult<out T> {
    data object Idle: UiResult<Nothing>()
    data object Loading: UiResult<Nothing>()
    data class Success<T>(val data: T): UiResult<T>()
    data class Error(val message: String?): UiResult<Nothing>()

    val isLoading get() = this is Loading
    val isSuccess get() = this is Success
    val isError get() = this is Error

    fun getOrNull(): T? = if (this is Success) this.data else null
    fun errorOrNull(): String? = if (this is Error) this.message else null
}