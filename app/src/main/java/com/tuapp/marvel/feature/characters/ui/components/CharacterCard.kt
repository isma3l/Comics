package com.tuapp.marvel.feature.characters.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tuapp.marvel.core.ui.components.FavoriteIcon
import com.tuapp.marvel.feature.characters.domain.model.Character

@Composable
fun CharacterCard(
    character: Character,
    onComicClick: (Int) -> Unit,
    onToggleFavorite: (character: Character) -> Unit
) {
    Column(
        modifier = Modifier.Companion
            .clickable { onComicClick(character.id) }
    ) {
        Box {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                contentScale = ContentScale.Companion.Crop,
                alignment = Alignment.Companion.TopCenter,
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(185.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
            )
            FavoriteIcon(
                modifier = Modifier.Companion.align(Alignment.Companion.TopEnd),
                onToggleFavorite = { onToggleFavorite(character) },
                isFavorite = character.isFavorite
            )
        }
        Text(
            text = character.name.replaceFirstChar { it.uppercase() },
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.Companion
                .padding(top = 6.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}