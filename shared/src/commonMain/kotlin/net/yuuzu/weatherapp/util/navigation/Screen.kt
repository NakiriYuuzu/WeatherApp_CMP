package net.yuuzu.weatherapp.util.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String ?= null,
    val navIcon: ImageVector ?= null,
    val objectName: String? = null,
    val objectPath: String? = null
) {
    object HomeScreen: Screen("home_screen", "Home", Icons.Filled.Home)
    object SearchScreen: Screen("search_screen", "Search", Icons.Filled.Search)
    object FavoriteScreen: Screen("favorite_screen", "Favorite", Icons.Filled.Favorite)
}