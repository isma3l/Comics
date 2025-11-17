package com.tuapp.marvel.feature.characters.domain.repository

import com.tuapp.marvel.feature.characters.domain.model.Character
import com.tuapp.marvel.feature.characters.domain.model.CharacterDetail
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(name: String? = null): Flow<List<Character>>
    suspend fun getCharacterDetail(characterId: Int): Result<CharacterDetail>
    fun getFavorites(name: String?): Flow<List<Character>>
    suspend fun addFavorite(character: Character): Result<Unit>
    suspend fun removeFavorite(character: Character): Result<Unit>
    fun getFavoriteIds(): Flow<List<Int>>
    suspend fun isFavorite(characterId: Int): Result<Boolean>
}