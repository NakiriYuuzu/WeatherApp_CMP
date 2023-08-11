package net.yuuzu.weatherapp.core.data

import com.russhwolf.settings.ObservableSettings

expect class PreferencesDriver {
    fun provideSettings(): ObservableSettings
}