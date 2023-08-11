package net.yuuzu.weatherapp.weather.presentation.search

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.safeDrawing
import moe.tlaster.precompose.viewmodel.viewModel
import net.yuuzu.weatherapp.weather.presentation.home.components.CenterElement
import net.yuuzu.weatherapp.weather.presentation.home.components.ExpandableCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSoftwareKeyboardApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun SearchScreen(padding: PaddingValues) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val viewModel: SearchViewModel = viewModel(SearchViewModel::class) { SearchViewModel() }
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = state.isError.isNotEmpty()) {
        if (state.isError.isNotEmpty()) {
            snackbarHostState.showSnackbar(
                message = state.isError,
                actionLabel = null,
                duration = SnackbarDuration.Long
            )
        }
    }

    LaunchedEffect(key1 = state.showSnackbar) {
        if (state.showSnackbar) {
            val snackbarResult = snackbarHostState.showSnackbar(
                message = "Add ${state.query} to favorite?",
                duration = SnackbarDuration.Long,
                actionLabel = "Add",
            )

            when (snackbarResult) {
                SnackbarResult.Dismissed -> {
                    viewModel.onEvent(SearchEvent.DismissSnackbar)
                }
                SnackbarResult.ActionPerformed -> {
                    viewModel.onEvent(SearchEvent.AddToFavorite(state.query))
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.absoluteOffset(y = (-96).dp)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
                )
        ) },
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
            .pointerInput(Unit) { detectTapGestures { keyboardController?.hide() } }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "Search",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.query,
                onValueChange = { viewModel.onEvent(SearchEvent.Search(it)) },
                placeholder = { Text("Search") },
                supportingText = { Text("Input Your City") },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (state.isLoading) {
                CenterElement {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                if (state.isCompleted) {
                    ExpandableCard(
                        state = state.weatherResult,
                        expendable = true,
                        addToFavorite = {
                            viewModel.onEvent(SearchEvent.ShowSnackbar)
                        },
                        isAddToFavorite = true
                    )
                }
            }
        }
    }
}