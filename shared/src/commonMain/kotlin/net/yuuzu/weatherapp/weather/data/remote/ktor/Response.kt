package net.yuuzu.weatherapp.weather.data.remote.ktor

import io.ktor.client.statement.HttpResponse

data class Response<T>(
    val response: HttpResponse
)
