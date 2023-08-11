package net.yuuzu.weatherapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.rememberNavigator
import net.yuuzu.weatherapp.core.presentation.WeatherTheme
import net.yuuzu.weatherapp.util.navigation.Navigation
import net.yuuzu.weatherapp.util.navigation.Screen
import net.yuuzu.weatherapp.util.navigation.currentRoute
import net.yuuzu.weatherapp.weather.presentation.home.components.CustomBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    val navigator = rememberNavigator()

    WeatherTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(
                bottomBar = {
                    when (currentRoute(navigator)) {
                        Screen.HomeScreen.route, Screen.SearchScreen.route, Screen.FavoriteScreen.route -> {
                            CustomBottomBar(
                                navigator = navigator
                            )
                        }
                    }
                },
            ) { value ->
                Navigation(navigator, value)
            }
        }
    }
}