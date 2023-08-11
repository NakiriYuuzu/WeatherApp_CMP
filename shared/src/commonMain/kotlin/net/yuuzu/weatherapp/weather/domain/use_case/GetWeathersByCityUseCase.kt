package net.yuuzu.weatherapp.weather.domain.use_case

import net.yuuzu.weatherapp.weather.data.remote.models.WeatherResponse
import net.yuuzu.weatherapp.weather.domain.repository.WeatherRepository

class GetWeathersByCityUseCase internal constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(city: String, appid: String): Result<WeatherResponse> {
        return weatherRepository.getWeatherByCity(city, appid)
    }
}