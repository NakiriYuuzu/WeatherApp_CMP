package net.yuuzu.weatherapp.weather.presentation.favorite

import net.yuuzu.weatherapp.weather.domain.models.WeatherResult

data class FavoriteState(
    val weatherList: List<WeatherResult> = emptyList(),
    val isLoading: Boolean = false,
)
