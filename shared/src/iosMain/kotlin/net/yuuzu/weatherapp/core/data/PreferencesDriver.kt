package net.yuuzu.weatherapp.core.data

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import platform.Foundation.NSUserDefaults

actual class PreferencesDriver {
    actual fun provideSettings(): ObservableSettings {
        val nsUserDefault = NSUserDefaults.standardUserDefaults
        return NSUserDefaultsSettings(delegate = nsUserDefault)
    }
}