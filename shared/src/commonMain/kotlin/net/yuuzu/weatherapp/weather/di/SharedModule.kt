package net.yuuzu.weatherapp.weather.di

private val sharedModuleList = listOf(
    dataModule,
    domainModule,
    preferencesModule()
)

fun getSharedModule() = sharedModuleList