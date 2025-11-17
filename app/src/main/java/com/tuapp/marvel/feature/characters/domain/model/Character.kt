package com.tuapp.marvel.feature.characters.domain.model

data class Character(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean = false
)