package com.tuapp.marvel.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteIcon(
    modifier: Modifier,
    onToggleFavorite: () -> Unit,
    isFavorite: Boolean,
    size: Dp = 24.dp
) {
    IconButton(
        onClick = onToggleFavorite,
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier.size(size),
            imageVector = Icons.Default.Favorite,
            tint = if (isFavorite) Color.Red.copy(alpha = 0.7f) else Color.Black.copy(alpha = 0.7f),
            contentDescription = "add Favorite"
        )
    }
}
