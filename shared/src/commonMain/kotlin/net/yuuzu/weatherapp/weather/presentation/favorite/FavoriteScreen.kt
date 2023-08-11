package net.yuuzu.weatherapp.weather.presentation.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.viewmodel.viewModel
import net.yuuzu.weatherapp.weather.presentation.home.components.CenterElement
import net.yuuzu.weatherapp.weather.presentation.home.components.ExpandableCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(padding: PaddingValues) {
    val viewModel: FavoriteViewModel = viewModel(FavoriteViewModel::class) { FavoriteViewModel() }
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.padding(bottom = 80.dp)
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .padding(16.dp)
        ) {
            Text(
                text = "Favorite",
                style = MaterialTheme.typography.titleLarge
            )
            if (state.isLoading) {
                CenterElement {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    state.weatherList.forEach {
                        item {
                            ExpandableCard(
                                state = it,
                                expendable = true
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}