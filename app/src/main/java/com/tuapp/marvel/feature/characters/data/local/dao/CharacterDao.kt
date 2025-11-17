package com.tuapp.marvel.feature.characters.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tuapp.marvel.feature.characters.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM favorites WHERE name LIKE '%' || :name || '%'")
    fun searchFavorites(name: String): Flow<List<CharacterEntity>>

    @Query("SELECT id FROM favorites")
    fun getFavoritesIds(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(characterEntity: CharacterEntity)

    @Delete
    suspend fun removeFavorite(characterEntity: CharacterEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}