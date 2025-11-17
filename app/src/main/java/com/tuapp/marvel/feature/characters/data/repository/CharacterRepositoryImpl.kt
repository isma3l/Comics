package com.tuapp.marvel.feature.characters.data.repository

import com.tuapp.marvel.feature.characters.data.datasource.CharactersLocalDataSource
import com.tuapp.marvel.feature.characters.data.datasource.CharactersRemoteDataSource
import com.tuapp.marvel.feature.characters.data.mapper.CharacterMapper
import com.tuapp.marvel.feature.characters.domain.model.Character
import com.tuapp.marvel.feature.characters.domain.model.CharacterDetail
import com.tuapp.marvel.feature.characters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val localDataSource: CharactersLocalDataSource,
    private val remoteDataSource: CharactersRemoteDataSource,
    private val mapper: CharacterMapper
): CharacterRepository {
    override fun getCharacters(name: String?): Flow<List<Character>> =
        remoteDataSource.getCharacters(name).map {
            characters -> characters.map { mapper.toDomain(it)}
        }

    override suspend fun getCharacterDetail(characterId: Int): Result<CharacterDetail> {
        return runCatching {
            remoteDataSource.getCharacterDetail(characterId).let(mapper::toDetailDomain)
        }
    }

    override fun getFavorites(name: String?): Flow<List<Character>> =
        localDataSource.getFavorites(name).map { favorites ->
            favorites.map { mapper.fromEntity(it) }
        }

    override suspend fun addFavorite(character: Character): Result<Unit> {
        return runCatching {
            localDataSource.addFavorite(mapper.toEntity(character))
        }
    }

    override suspend fun removeFavorite(character: Character): Result<Unit> {
        return runCatching {
            localDataSource.removeFavorite(mapper.toEntity(character))
        }
    }

    override fun getFavoriteIds(): Flow<List<Int>> = localDataSource.getFavoritesId()

    override suspend fun isFavorite(characterId: Int): Result<Boolean> {
        return runCatching {
            localDataSource.isFavorite(characterId)
        }
    }
}