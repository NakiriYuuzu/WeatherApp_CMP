package net.yuuzu.weatherapp.weather.presentation.search

import net.yuuzu.weatherapp.weather.domain.models.WeatherResult

data class SearchState(
    val query: String = "",
    val showSnackbar: Boolean = false,
    val isLoading: Boolean = false,
    val isError: String = "",
    val isCompleted: Boolean = false,
    val weatherResult: WeatherResult = WeatherResult.DEFAULT
)
