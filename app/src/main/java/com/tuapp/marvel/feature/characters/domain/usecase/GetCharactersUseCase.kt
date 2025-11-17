package com.tuapp.marvel.feature.characters.domain.usecase

import com.tuapp.marvel.feature.characters.domain.model.Character
import com.tuapp.marvel.feature.characters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(name: String? = null): Flow<List<Character>> {
        return combine(
            repository.getCharacters(name),
            repository.getFavoriteIds()
        ) { characters, favoriteIds ->
            characters.map { it.copy(isFavorite = it.id in favoriteIds) }
        }
    }
}