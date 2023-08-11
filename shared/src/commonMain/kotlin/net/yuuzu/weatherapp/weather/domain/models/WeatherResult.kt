package net.yuuzu.weatherapp.weather.domain.models

data class WeatherResult(
    val dt: Long,
    val dateTime: String,
    val city: String,
    val country: String,
    val temperature: String,
    val lowHigh: String,
    val feelsLike: String,
    val humidity: String,
    val pressure: String,
    val visibility: String,
    val windSpeed: String,
    val weatherType: WeatherType,
    val weatherFuture: List<WeatherFuture>
) {
    companion object {
        val DEFAULT = WeatherResult(
            dt = 0,
            dateTime = "NAN",
            city = "NAN",
            country = "NAN",
            temperature = "NAN",
            lowHigh = "NAN",
            feelsLike = "NAN",
            humidity = "NAN",
            pressure = "NAN",
            visibility = "NAN",
            windSpeed = "NAN",
            weatherType = WeatherType.fromWeather(0),
            weatherFuture = emptyList()
        )
    }
}