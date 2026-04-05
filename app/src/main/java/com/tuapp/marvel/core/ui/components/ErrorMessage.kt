package com.tuapp.marvel.core.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorMessage(
    message: String?,
    modifier: Modifier = Modifier
) {
    Text(
        text = message ?: "Error desconocido",
        color = MaterialTheme.colorScheme.error,
        modifier = modifier
    )
}