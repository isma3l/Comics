package com.tuapp.marvel.app.navigation

import kotlinx.serialization.Serializable

sealed class AppDestinations {
    @Serializable object CharacterList
    @Serializable object Favorites
    @Serializable data class CharacterDetail(val characterId: Int)
}