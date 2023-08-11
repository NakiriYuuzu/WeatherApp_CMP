package net.yuuzu.weatherapp.core.data

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

actual class PreferencesDriver(private val context: Context) {
    actual fun provideSettings(): ObservableSettings {
        val sharedPreferences =
            context.getSharedPreferences("app_preference", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(delegate = sharedPreferences)
    }
}