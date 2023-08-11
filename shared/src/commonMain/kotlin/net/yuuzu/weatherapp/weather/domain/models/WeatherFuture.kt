package net.yuuzu.weatherapp.weather.domain.models

data class WeatherFuture(
    val dateTime: String,
    val temperature: String,
    val weatherType: WeatherType
)
