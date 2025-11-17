package com.tuapp.marvel.feature.characters.data.network

import com.tuapp.marvel.feature.characters.data.dto.CharacterDetailDto
import com.tuapp.marvel.feature.characters.data.dto.CharacterDto
import com.tuapp.marvel.feature.characters.data.dto.ComicVineResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicVineApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json",
        @Query("limit") limit: Int = 8,
        @Query("filter") filter: String? = null,
        @Query("field_list") fieldList: String ="id,name,image,api_detail_url"
    ): ComicVineResponseDto<List<CharacterDto>>

    @GET("character/4005-{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: Int,
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json",
        @Query("field_list") fieldList: String = "id,name,image,deck"
    ): ComicVineResponseDto<CharacterDetailDto>
}