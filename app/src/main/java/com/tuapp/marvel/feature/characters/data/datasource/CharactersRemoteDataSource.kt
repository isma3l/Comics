package com.tuapp.marvel.feature.characters.data.datasource

import com.tuapp.marvel.feature.characters.data.dto.CharacterDto
import com.tuapp.marvel.feature.characters.data.network.ComicVineApiService
import javax.inject.Inject
import com.tuapp.marvel.BuildConfig
import com.tuapp.marvel.feature.characters.data.dto.CharacterDetailDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersRemoteDataSource @Inject constructor(
    private val apiService: ComicVineApiService
) {
    fun getCharacters(name: String? = null): Flow<List<CharacterDto>> = flow {
         val data = apiService.getCharacters(
            apiKey = BuildConfig.COMIC_VINE_API_KEY,
            filter = name?.let { "name:$it" }
        ).results
        emit(data)
    }

    suspend fun getCharacterDetail(characterId: Int): CharacterDetailDto {
        return apiService.getCharacterDetail(
            characterId,
            apiKey = BuildConfig.COMIC_VINE_API_KEY
        ).results
    }
}