package net.yuuzu.weatherapp.weather.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val dt: Long,
    val dt_txt: String,
    val visibility: Int,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
)