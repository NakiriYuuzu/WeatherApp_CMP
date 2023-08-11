package net.yuuzu.weatherapp.weather.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.viewmodel.viewModel
import net.yuuzu.weatherapp.weather.presentation.home.components.CenterElement
import net.yuuzu.weatherapp.weather.presentation.home.components.ExpandableCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(padding: PaddingValues) {
    val viewModel: HomeViewModel = viewModel(HomeViewModel::class) { HomeViewModel() }
    val snackbarHostState = remember { SnackbarHostState() }
    val isLoading = remember { mutableStateOf(false) }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.error) {
        if (state.error != null) {
            snackbarHostState.showSnackbar(
                message = state.error!!,
                actionLabel = null,
                duration = SnackbarDuration.Short
            )
        }
    }

    LaunchedEffect(state.isLoading) {
        if (state.isLoading) {
            isLoading.value = true
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.absoluteOffset(y = (-72).dp)
        ) },
        modifier = Modifier.padding(bottom = 80.dp),
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .padding(16.dp)
        ) {
            Text(
                text = "Home",
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (state.isLoading) {
                CenterElement {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                ExpandableCard(
                    state = state.weatherResult
                )
            }
        }
    }
}