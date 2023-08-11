package net.yuuzu.weatherapp.weather.data.repository

import net.yuuzu.weatherapp.weather.data.mapper.ResponseMapper
import net.yuuzu.weatherapp.weather.data.mapper.guard
import net.yuuzu.weatherapp.weather.data.remote.Api
import net.yuuzu.weatherapp.weather.data.remote.WeatherApi
import net.yuuzu.weatherapp.weather.data.remote.models.WeatherResponse
import net.yuuzu.weatherapp.weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val ktor: Api,
    private val responseMapper: ResponseMapper
): WeatherRepository {
    override suspend fun getWeathers(
        lat: Double,
        lon: Double,
        appid: String
    ): Result<WeatherResponse> {
        val response = Result.runCatching {
            ktor.request(WeatherApi.getWeathers(lat, lon, appid))
        }.guard { return it }

        return responseMapper.map(response)
    }

    override suspend fun getWeatherByCity(city: String, appid: String): Result<WeatherResponse> {
        val response = Result.runCatching {
            ktor.request(WeatherApi.getWeatherByCity(city, appid))
        }.guard { return it }

        return responseMapper.map(response)
    }
}