package net.yuuzu.weatherapp.weather.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherData>,
    val city: City
)