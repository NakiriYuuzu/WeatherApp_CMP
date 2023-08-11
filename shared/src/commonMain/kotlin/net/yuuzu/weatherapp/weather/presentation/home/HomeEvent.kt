package net.yuuzu.weatherapp.weather.presentation.home

sealed interface HomeEvent {
    data class LoadWeatherData(val lat: Double, val lon: Double) : HomeEvent
}