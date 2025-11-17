package com.tuapp.marvel.core.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.tuapp.marvel.app.navigation.AppDestinations

@Composable
fun AppBottomBar(
    currentDestination: NavDestination?,
    onNavigateToMovieList: () -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentDestination?.hasRoute<AppDestinations.CharacterList>() == true,
            onClick = onNavigateToMovieList,
            icon = { Icon(Icons.Default.Home, contentDescription = "Comics") },
            label = { Text("Comics") }
        )
        NavigationBarItem(
            selected = currentDestination?.hasRoute<AppDestinations.Favorites>() == true,
            onClick = onNavigateToFavorites,
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") }
        )
    }
}