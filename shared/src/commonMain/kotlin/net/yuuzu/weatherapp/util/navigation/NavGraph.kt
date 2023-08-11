package net.yuuzu.weatherapp.util.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.Dp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import net.yuuzu.weatherapp.weather.presentation.favorite.FavoriteScreen
import net.yuuzu.weatherapp.weather.presentation.home.HomeScreen
import net.yuuzu.weatherapp.weather.presentation.search.SearchScreen

@Composable
fun Navigation(navigator: Navigator, padding: PaddingValues) {
    NavHost(
        navigator = navigator,
        initialRoute = Screen.HomeScreen.route,
    ) {
        scene(route = Screen.HomeScreen.route) {
            HomeScreen(padding)
        }
        scene(route = Screen.SearchScreen.route) {
            SearchScreen(padding)
        }
        scene(route = Screen.FavoriteScreen.route) {
            FavoriteScreen(padding)
        }
    }
}

@Composable
fun currentRoute(navigator: Navigator): String? {
    return navigator.currentEntry.collectAsState(null).value?.route?.route
}