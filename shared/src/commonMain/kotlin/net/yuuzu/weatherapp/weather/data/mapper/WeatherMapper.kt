package net.yuuzu.weatherapp.weather.data.mapper

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import net.yuuzu.weatherapp.util.extension.toCelsius
import net.yuuzu.weatherapp.weather.data.remote.models.WeatherData
import net.yuuzu.weatherapp.weather.data.remote.models.WeatherResponse
import net.yuuzu.weatherapp.weather.domain.models.WeatherFuture
import net.yuuzu.weatherapp.weather.domain.models.WeatherResult
import net.yuuzu.weatherapp.weather.domain.models.WeatherType

val timeZone = TimeZone.currentSystemDefault()
val now = Clock.System.now().toLocalDateTime(timeZone)
val nowEpoch = Instant.parse(now.toString() + "Z").epochSeconds

fun WeatherResponse.toWeatherResult(): WeatherResult {
    val futureWeather = list.filter {  weatherData ->
        weatherData.dt >= nowEpoch
    }

    val currentWeather = futureWeather.first()

    return WeatherResult(
        dt = currentWeather.dt,
        dateTime = currentWeather.dt_txt,
        city = city.name,
        country = city.country,
        temperature = toCelsius(currentWeather.main.temp),
        lowHigh = toCelsius(currentWeather.main.temp_min) + " / " + toCelsius(currentWeather.main.temp_max),
        feelsLike = toCelsius(currentWeather.main.feels_like),
        humidity = "${currentWeather.main.humidity}%",
        pressure = "${currentWeather.main.pressure}mPa",
        visibility = "${currentWeather.visibility}m",
        windSpeed = "${currentWeather.wind.speed}km/h",
        weatherType = WeatherType.fromWeather(currentWeather.weather[0].id),
        weatherFuture = futureWeather.map { it.toFutureWeather() }
    )
}

fun WeatherData.toFutureWeather(): WeatherFuture {
    return WeatherFuture(
        dateTime = dt_txt,
        temperature = toCelsius(main.temp),
        weatherType = WeatherType.fromWeather(weather[0].id)
    )
}