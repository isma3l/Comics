package com.tuapp.marvel.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tuapp.marvel.core.ui.navigation.AppBottomBar
import com.tuapp.marvel.core.ui.navigation.AppTopBar
import com.tuapp.marvel.feature.characters.ui.characterList.CharacterListScreen
import com.tuapp.marvel.feature.characters.ui.detail.CharacterDetailScreen
import com.tuapp.marvel.feature.characters.ui.favorites.FavoritesScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    val isDetailScreen = currentDestination?.hasRoute<AppDestinations.CharacterDetail>() == true

    Scaffold(
       topBar = {
           if (isDetailScreen) {
               AppTopBar(
                   showBackButton = true,
                   navigateBack = { navController.popBackStack() }
               )
           }
       },
        bottomBar = {
            if (!isDetailScreen) {
                AppBottomBar(
                    currentDestination = currentDestination,
                    onNavigateToFavorites = {
                        navController.navigate(AppDestinations.Favorites) {
                            popUpTo(AppDestinations.CharacterList) { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                    onNavigateToMovieList = {
                        navController.navigate(AppDestinations.CharacterList) {
                            popUpTo(AppDestinations.CharacterList) { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestinations.CharacterList,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<AppDestinations.CharacterList> {
                CharacterListScreen(onComicClick = {
                    comicId -> navController.navigate(AppDestinations.CharacterDetail(comicId))
                })
            }
            composable<AppDestinations.CharacterDetail> {
                CharacterDetailScreen()
            }
            composable<AppDestinations.Favorites> {
                FavoritesScreen(onComicClick = {
                    comicId -> navController.navigate(AppDestinations.CharacterDetail(comicId))
                })
            }
        }

    }
}