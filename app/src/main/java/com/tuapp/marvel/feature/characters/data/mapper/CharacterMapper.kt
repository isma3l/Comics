package com.tuapp.marvel.feature.characters.data.mapper

import com.tuapp.marvel.feature.characters.data.dto.CharacterDetailDto
import com.tuapp.marvel.feature.characters.data.dto.CharacterDto
import com.tuapp.marvel.feature.characters.data.local.entity.CharacterEntity
import com.tuapp.marvel.feature.characters.domain.model.Character
import com.tuapp.marvel.feature.characters.domain.model.CharacterDetail
import javax.inject.Inject

class CharacterMapper @Inject constructor() {
    fun toDomain(dto: CharacterDto): Character {
        return Character(
            id = dto.id,
            name = dto.name,
            imageUrl = dto.image?.smallUrl ?: "",
        )
    }

    fun toEntity(character: Character): CharacterEntity {
        return CharacterEntity(
            id = character.id,
            name = character.name,
            imageUrl = character.imageUrl,
            isFavorite = character.isFavorite
        )
    }

    fun fromEntity(characterEntity: CharacterEntity): Character {
        return Character(
            id = characterEntity.id,
            name = characterEntity.name,
            imageUrl = characterEntity.imageUrl,
            isFavorite = characterEntity.isFavorite
        )
    }

    fun toDetailDomain(dto: CharacterDetailDto): CharacterDetail {
        return CharacterDetail(
            id = dto.id,
            name = dto.name,
            imageUrl = dto.image?.smallUrl ?: "",
            description = dto.description
        )
    }
}