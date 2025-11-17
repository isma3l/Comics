package com.tuapp.marvel.feature.characters.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tuapp.marvel.feature.characters.data.local.dao.CharacterDao
import com.tuapp.marvel.feature.characters.data.local.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
abstract class ComicVineDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}