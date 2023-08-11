package net.yuuzu.weatherapp.core.data

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import com.russhwolf.settings.set
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PreferencesManager: KoinComponent {
    private val preferenceSettings: PreferencesDriver by inject()

    private val observableSettings = preferenceSettings.provideSettings()

    fun getString(key: String) = observableSettings.getStringOrNull(key = key)

    @OptIn(ExperimentalSettingsApi::class)
    fun getStringAsFlow(key: String) = observableSettings.getStringOrNullFlow(key = key)

    fun setString(key: String, value: String) {
        observableSettings.set(key = key, value = value)
    }

    fun getStrings(key: String): List<String> {
        return getString(key)?.split(",") ?: emptyList()
    }

    fun setStrings(key: String, value: String) {
        getString(key)?.let {
            val newValue = "$it,$value"
            observableSettings.set(key = key, value = newValue)
        } ?: run {
            observableSettings.set(key = key, value = value)
        }
    }

    companion object {
        const val favoriteCityKey = "favorite_city"
    }
}