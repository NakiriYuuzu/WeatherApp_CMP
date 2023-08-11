package net.yuuzu.weatherapp.weather.di

import net.yuuzu.weatherapp.weather.data.mapper.ResponseMapper
import net.yuuzu.weatherapp.weather.data.remote.Api
import net.yuuzu.weatherapp.weather.data.remote.KtorRequest
import net.yuuzu.weatherapp.weather.data.remote.getHttpClient
import net.yuuzu.weatherapp.weather.data.repository.WeatherRepositoryImpl
import net.yuuzu.weatherapp.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single { getHttpClient() }
    single<Api> { KtorRequest(client = get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(ktor = get(), responseMapper = get()) }
    factory { ResponseMapper() }
}