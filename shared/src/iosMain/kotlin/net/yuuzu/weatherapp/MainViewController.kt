package net.yuuzu.weatherapp

import moe.tlaster.precompose.PreComposeApplication
import net.yuuzu.weatherapp.weather.di.initKoin
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    val isDarkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
            UIUserInterfaceStyle.UIUserInterfaceStyleDark

    initKoin()

    return PreComposeApplication(title = "") {
        App(
            darkTheme = isDarkTheme,
            dynamicColor = false,
        )
    }
}