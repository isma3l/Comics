package com.tuapp.marvel.feature.characters.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean
)