package net.yuuzu.weatherapp.weather.domain.use_case

import net.yuuzu.weatherapp.weather.data.remote.models.WeatherResponse
import net.yuuzu.weatherapp.weather.domain.repository.WeatherRepository

class GetWeathersUseCase internal constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double, appid: String): Result<WeatherResponse> {
        return weatherRepository.getWeathers(lat, lon, appid)
    }
}