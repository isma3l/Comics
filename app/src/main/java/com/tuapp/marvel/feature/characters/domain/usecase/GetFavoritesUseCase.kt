package com.tuapp.marvel.feature.characters.domain.usecase

import com.tuapp.marvel.feature.characters.domain.model.Character
import com.tuapp.marvel.feature.characters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(name: String? = null): Flow<List<Character>> = repository.getFavorites(name)

}