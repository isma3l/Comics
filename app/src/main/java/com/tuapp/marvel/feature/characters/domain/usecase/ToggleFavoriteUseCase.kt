package com.tuapp.marvel.feature.characters.domain.usecase

import android.util.Log
import com.tuapp.marvel.feature.characters.domain.model.Character
import com.tuapp.marvel.feature.characters.domain.repository.CharacterRepository
import jakarta.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(character: Character): Result<Unit> {
        return if (character.isFavorite) {
            repository.removeFavorite(character)
        } else {
            repository.addFavorite(character.copy(isFavorite = true))
        }
    }
}