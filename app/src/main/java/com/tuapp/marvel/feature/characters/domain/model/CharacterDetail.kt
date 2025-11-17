package com.tuapp.marvel.feature.characters.domain.model

data class CharacterDetail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val isFavorite: Boolean = false
) {
    fun toCharacter(): Character {
        return Character(
            id = id,
            name = name,
            imageUrl = imageUrl,
            isFavorite = isFavorite
        )
    }
}