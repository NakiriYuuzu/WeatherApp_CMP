package net.yuuzu.weatherapp.weather.data.remote

import net.yuuzu.weatherapp.weather.data.remote.ktor.ApiMethod
import net.yuuzu.weatherapp.weather.data.remote.ktor.ApiRequest
import net.yuuzu.weatherapp.weather.data.remote.ktor.Query
import net.yuuzu.weatherapp.weather.data.remote.models.WeatherResponse

internal object WeatherApi {
    fun getWeathers(
        lat: Double,
        lon: Double,
        appid: String,
    ) = ApiRequest<WeatherResponse>(
        method = ApiMethod.GET,
        path = "forecast?",
        parameters = listOf(
            Query("lat", "$lat"),
            Query("lon", "$lon"),
            Query("appid", appid)
        )
    )

    fun getWeatherByCity(
        city: String,
        appid: String,
    ) = ApiRequest<WeatherResponse>(
        method = ApiMethod.GET,
        path = "forecast?",
        parameters = listOf(
            Query("q", city),
            Query("appid", appid)
        )
    )
}