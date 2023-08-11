package net.yuuzu.weatherapp.weather.di

import net.yuuzu.weatherapp.core.data.PreferencesDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun preferencesModule(): Module = module {
    factory { PreferencesDriver() }
}