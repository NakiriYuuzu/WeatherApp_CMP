package net.yuuzu.weatherapp.weather.domain.models

import dev.icerock.moko.resources.ImageResource
import net.yuuzu.weatherapp.SharedRes

sealed class WeatherType(
    val weatherDesc: String,
    val iconRes: ImageResource
) {
    object Sunny : WeatherType(
        weatherDesc = "ClearSky",
        iconRes = SharedRes.images.sunny
    )

    object Cloudy : WeatherType(
        weatherDesc = "Cloudy",
        iconRes = SharedRes.images.cloudy
    )

    object Rainy : WeatherType(
        weatherDesc = "Rainy",
        iconRes = SharedRes.images.rain
    )

    object Snowy : WeatherType(
        weatherDesc = "Snowy",
        iconRes = SharedRes.images.snow
    )

    object Thunder : WeatherType(
        weatherDesc = "Thunder",
        iconRes = SharedRes.images.thunder
    )

    object Thunderstorm : WeatherType(
        weatherDesc = "Thunderstorm",
        iconRes = SharedRes.images.thunderstorm
    )

    companion object {
        fun fromWeather(code: Int): WeatherType {
            return when(code) {
                in 200..233 -> Thunderstorm
                in 300..302 -> Rainy
                in 500..549 -> Rainy
                in 600..623 -> Snowy
                800 -> Sunny
                in 801..804 -> Cloudy
                in 900..904 -> Thunder
                else -> Sunny
            }
        }
    }
}