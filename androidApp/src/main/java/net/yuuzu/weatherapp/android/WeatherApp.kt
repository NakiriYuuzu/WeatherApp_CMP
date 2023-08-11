package net.yuuzu.weatherapp.android

import android.app.Application
import net.yuuzu.weatherapp.weather.di.getSharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(modules = getSharedModule())
        }
    }
}