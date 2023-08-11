package net.yuuzu.weatherapp.core.presentation

import androidx.compose.runtime.Composable

@Composable
expect fun WeatherTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)