package net.yuuzu.weatherapp.weather.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

// for iOS
fun initKoin() {
    startKoin {
        modules(modules = getSharedModule())
    }
}