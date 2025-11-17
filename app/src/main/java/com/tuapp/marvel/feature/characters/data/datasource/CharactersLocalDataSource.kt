package com.tuapp.marvel.feature.characters.data.datasource

import com.tuapp.marvel.feature.characters.data.local.dao.CharacterDao
import com.tuapp.marvel.feature.characters.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersLocalDataSource @Inject constructor(
    private val characterDao: CharacterDao
) {
    fun getFavorites(name: String? = null): Flow<List<CharacterEntity>> {
         return if (name.isNullOrEmpty()) {
            characterDao.getFavorites()
        } else {
            characterDao.searchFavorites(name)
        }
    }

    fun getFavoritesId(): Flow<List<Int>> = characterDao.getFavoritesIds()

    suspend fun addFavorite(character: CharacterEntity) {
        characterDao.addFavorite(character)
    }

    suspend fun removeFavorite(character: CharacterEntity) {
        characterDao.removeFavorite(character)
    }

    suspend fun isFavorite(characterId: Int): Boolean {
        return characterDao.isFavorite((characterId))
    }
}