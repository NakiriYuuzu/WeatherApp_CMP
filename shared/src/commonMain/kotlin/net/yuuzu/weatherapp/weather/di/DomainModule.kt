package net.yuuzu.weatherapp.weather.di

import net.yuuzu.weatherapp.weather.domain.use_case.GetWeathersByCityUseCase
import net.yuuzu.weatherapp.weather.domain.use_case.GetWeathersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetWeathersUseCase(weatherRepository = get()) }
    factory { GetWeathersByCityUseCase(weatherRepository = get()) }
}