package net.yuuzu.weatherapp.weather.data.remote

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpCallValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun getHttpClient() = HttpClient {
    install(HttpCallValidator)
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
        )
    }
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Napier.d(message, tag = "HTTP Client")
            }
        }
        level = LogLevel.BODY
    }.also { Napier.base(DebugAntilog()) }
}