package net.yuuzu.weatherapp.weather.data.remote.ktor

import net.yuuzu.weatherapp.util.DataConfig.weatherApi

data class ApiRequest<T>(
    val method: ApiMethod,
    val url: String = weatherApi,
    val path: String,
    val requestBody: Any? = null,
    val parameters: List<Query>? = null
)