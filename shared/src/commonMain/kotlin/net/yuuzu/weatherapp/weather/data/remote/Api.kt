package net.yuuzu.weatherapp.weather.data.remote

import net.yuuzu.weatherapp.weather.data.remote.ktor.ApiRequest
import net.yuuzu.weatherapp.weather.data.remote.ktor.Response

interface Api {
    suspend fun <T>request(request: ApiRequest<T>): Response<T>
}