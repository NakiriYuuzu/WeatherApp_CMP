package net.yuuzu.weatherapp.weather.presentation.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import net.yuuzu.weatherapp.util.DataConfig
import net.yuuzu.weatherapp.weather.data.mapper.toWeatherResult
import net.yuuzu.weatherapp.weather.domain.use_case.GetWeathersUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel: ViewModel(), KoinComponent {
    private val weathersUseCase: GetWeathersUseCase by inject()

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getWeathers(24.264286,120.5624469)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadWeatherData -> {
                getWeathers(event.lat, event.lon)
            }
        }
    }

    private fun getWeathers(lat: Double,lon: Double) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            weathersUseCase(lat, lon, DataConfig.weatherApiKey)
                .onFailure { error ->
                    _state.value = HomeState(error = error.message)
                }
                .onSuccess { result ->
                    _state.update { it.copy(
                        weatherResult = result.toWeatherResult()
                    ) }
                }
            _state.update { it.copy(isLoading = false) }
        }
    }
}