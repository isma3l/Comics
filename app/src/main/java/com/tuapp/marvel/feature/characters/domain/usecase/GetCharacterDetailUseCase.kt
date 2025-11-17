package com.tuapp.marvel.feature.characters.domain.usecase;

import com.tuapp.marvel.feature.characters.domain.model.CharacterDetail
import com.tuapp.marvel.feature.characters.domain.repository.CharacterRepository
import javax.inject.Inject;

class GetCharacterDetailUseCase @Inject constructor(
     private val repository: CharacterRepository
) {

    suspend operator fun invoke(characterId: Int): Result<CharacterDetail> {
        return runCatching {
            val characterDetail = repository.getCharacterDetail(characterId).getOrThrow()
            val isFavorite = repository.isFavorite(characterId).getOrThrow()
            characterDetail.copy(isFavorite = isFavorite)
        }
    }
}
