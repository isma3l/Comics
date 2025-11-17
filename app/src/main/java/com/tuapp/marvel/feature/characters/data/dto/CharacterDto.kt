package com.tuapp.marvel.feature.characters.data.dto

import com.google.gson.annotations.SerializedName

data class ComicVineResponseDto<T>(
    val error: String,
    val limit: Int,
    val offset: Int,
    @SerializedName("number_of_page_results")
    val numberOfPageResults: Int,
    @SerializedName("number_of_total_results")
    val numberOfTotalResults: Int,
    @SerializedName("status_code")
    val statusCode: Int,
    val results: T
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val image: CharacterImageDto?,
)

data class CharacterImageDto(
    @SerializedName("small_url")
    val smallUrl: String?
)

data class CharacterDetailDto(
    val id: Int,
    val name: String,
    val image: CharacterImageDto?,
    @SerializedName("deck")
    val description: String
)