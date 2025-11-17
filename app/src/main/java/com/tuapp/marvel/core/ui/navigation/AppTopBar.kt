package com.tuapp.marvel.core.ui.navigation

import kotlin.OptIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    showBackButton: Boolean,
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text("Details")
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "go back"
                    )
                }
            }
        }
    )
}