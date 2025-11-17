package com.tuapp.marvel.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.tuapp.marvel.feature.characters.domain.model.Character

@Composable
fun CharacterList(
    characters: List<Character>,
    onComicClick: (Int) -> Unit,
    onToggleFavorite: (character: Character) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(characters) { character ->
            CharacterCard(
                character = character,
                onComicClick = onComicClick,
                onToggleFavorite = onToggleFavorite
            )
        }
    }
}