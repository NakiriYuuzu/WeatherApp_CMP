package net.yuuzu.weatherapp.weather.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)
