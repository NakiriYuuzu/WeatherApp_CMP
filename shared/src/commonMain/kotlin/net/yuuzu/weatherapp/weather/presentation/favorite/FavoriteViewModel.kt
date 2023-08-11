package net.yuuzu.weatherapp.weather.presentation.favorite

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import net.yuuzu.weatherapp.core.data.PreferencesManager
import net.yuuzu.weatherapp.util.DataConfig
import net.yuuzu.weatherapp.weather.data.mapper.toWeatherResult
import net.yuuzu.weatherapp.weather.domain.models.WeatherResult
import net.yuuzu.weatherapp.weather.domain.use_case.GetWeathersByCityUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FavoriteViewModel: ViewModel(), KoinComponent {
    private val getWeathersByCityUseCase: GetWeathersByCityUseCase by inject()
    private val preferencesManager = PreferencesManager()

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> = _state.asStateFlow()

    init {
        getWeatherList()
    }

    private fun getWeatherList() {
        val cityLists = preferencesManager.getStrings(PreferencesManager.favoriteCityKey)
        viewModelScope.launch {
            val weatherList = mutableListOf<WeatherResult>()
            _state.update { it.copy(isLoading = true) }
            cityLists.forEach { city ->
                getWeathersByCityUseCase(city, DataConfig.weatherApiKey)
                    .onSuccess {
                        weatherList.add(it.toWeatherResult())
                    }
            }
            _state.update { it.copy(weatherList = weatherList, isLoading = false) }
        }
    }
}