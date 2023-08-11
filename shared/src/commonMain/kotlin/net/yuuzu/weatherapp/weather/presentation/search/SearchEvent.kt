package net.yuuzu.weatherapp.weather.presentation.search

sealed interface SearchEvent {
    data class Search(val query: String): SearchEvent
    data class ShowError(val error: String): SearchEvent
    data class AddToFavorite(val favorite: String): SearchEvent
    object ShowLoading: SearchEvent
    object ShowSnackbar: SearchEvent
    object DismissSnackbar: SearchEvent
    object OnCompleted: SearchEvent
}