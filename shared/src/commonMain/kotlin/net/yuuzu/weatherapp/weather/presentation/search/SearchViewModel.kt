package net.yuuzu.weatherapp.weather.presentation.search

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import net.yuuzu.weatherapp.core.data.PreferencesManager
import net.yuuzu.weatherapp.util.DataConfig
import net.yuuzu.weatherapp.weather.data.mapper.toWeatherResult
import net.yuuzu.weatherapp.weather.domain.use_case.GetWeathersByCityUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@OptIn(FlowPreview::class)
class SearchViewModel: ViewModel(), KoinComponent {
    private val getWeathersByCityUseCase: GetWeathersByCityUseCase by inject()
    private val preferencesManager = PreferencesManager()

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    private val searchQuery = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            searchQuery
                .filterNotNull()
                .distinctUntilChanged()
                .debounce(500)
                .collectLatest { query ->
                    getWeathersByCity(query)
                }
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Search -> {
                _state.update {it.copy(
                    query = event.query,
                    isCompleted = false,
                    isLoading = true,
                    showSnackbar = false
                ) }
                searchQuery.update { event.query }
            }
            is SearchEvent.ShowError -> {
                _state.update { it.copy(
                    isError = event.error,
                    isLoading = false,
                    isCompleted = false
                ) }
            }
            is SearchEvent.AddToFavorite -> {
                preferencesManager.setStrings(PreferencesManager.favoriteCityKey, event.favorite)
            }
            SearchEvent.ShowSnackbar -> {
                _state.update { it.copy(showSnackbar = true) }
            }
            SearchEvent.DismissSnackbar -> {
                _state.update { it.copy(showSnackbar = false) }
            }
            SearchEvent.ShowLoading -> {
                _state.update { it.copy(isLoading = true) }
            }
            SearchEvent.OnCompleted -> {
                _state.update { it.copy(isCompleted = true, isLoading = false) }
            }
        }
    }

    private fun getWeathersByCity(city: String) {
        viewModelScope.launch {
            onEvent(SearchEvent.ShowLoading)
            getWeathersByCityUseCase(city, DataConfig.weatherApiKey)
                .onFailure {
                    onEvent(SearchEvent.ShowError(it.message ?: "Unknown Error."))
                }
                .onSuccess { result ->
                    if (result.cod != "200") {
                        onEvent(SearchEvent.ShowError("Unknown City..."))
                        println(result.cod + " " + result.message)
                        return@onSuccess
                    }
                    _state.update { it.copy(weatherResult = result.toWeatherResult()) }
                    onEvent(SearchEvent.OnCompleted)
                }
        }
    }
}