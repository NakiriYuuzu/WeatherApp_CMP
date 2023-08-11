package net.yuuzu.weatherapp.weather.domain.repository

import net.yuuzu.weatherapp.weather.data.remote.models.WeatherResponse

interface WeatherRepository {
    suspend fun getWeathers(lat: Double, lon: Double, appid: String): Result<WeatherResponse>
    suspend fun getWeatherByCity(city: String, appid: String): Result<WeatherResponse>
}