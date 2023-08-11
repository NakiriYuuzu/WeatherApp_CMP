package net.yuuzu.weatherapp.util.extension

fun toCelsius(temp: Double): String {
    return "${(temp - 273.15).toInt()}°C"
}