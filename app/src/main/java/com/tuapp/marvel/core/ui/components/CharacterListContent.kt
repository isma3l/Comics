package com.tuapp.marvel.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tuapp.marvel.feature.characters.domain.model.Character
import com.tuapp.marvel.feature.characters.ui.characterList.components.CharacterSearch

@Composable
fun CharacterListContent(
    searchQuery: String,
    onQueryChange: (query: String) -> Unit,
    onSearch: () -> Unit,
    onCharacterClick: (Int) -> Unit,
    onToggleFavorite: (character: Character) -> Unit,
    isLoading: Boolean,
    error: String?,
    characters: List<Character>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        CharacterSearch(
            query = searchQuery,
            onQueryChange = onQueryChange,
            onSearch = onSearch
        )

        when {
            isLoading -> CircularProgressIndicator()
            error !== null -> Text(text = error)
            else -> CharacterList(
                characters = characters,
                onComicClick = onCharacterClick,
                onToggleFavorite = onToggleFavorite
            )
        }
    }
}