package net.yuuzu.weatherapp.weather.presentation.home

import net.yuuzu.weatherapp.weather.domain.models.WeatherResult

data class HomeState(
    val weatherResult: WeatherResult = WeatherResult.DEFAULT,
    val error: String? = null,
    val isLoading: Boolean = false,
)

