package net.yuuzu.weatherapp.weather.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class City(
    val id: Int,
    val name: String,
    val country: String,
)