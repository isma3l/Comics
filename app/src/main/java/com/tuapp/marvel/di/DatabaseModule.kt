package com.tuapp.marvel.di

import android.content.Context
import androidx.room.Room
import com.tuapp.marvel.feature.characters.data.local.ComicVineDatabase
import com.tuapp.marvel.feature.characters.data.local.dao.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMarvelDatabase(@ApplicationContext context: Context): ComicVineDatabase {
        return Room.databaseBuilder(
            context,
            ComicVineDatabase::class.java,
            "marvel_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(database: ComicVineDatabase): CharacterDao {
        return database.characterDao()
    }
}