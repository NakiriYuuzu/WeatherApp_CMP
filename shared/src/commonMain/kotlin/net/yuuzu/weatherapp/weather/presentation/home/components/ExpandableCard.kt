package net.yuuzu.weatherapp.weather.presentation.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import net.yuuzu.weatherapp.SharedRes
import net.yuuzu.weatherapp.weather.domain.models.WeatherResult

@Composable
fun ExpandableCard(
    state: WeatherResult,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(16.dp),
    cardColors: Color = MaterialTheme.colorScheme.primaryContainer,
    expendable: Boolean = false,
    addToFavorite: () -> Unit = {},
    isAddToFavorite: Boolean = false,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        if (expendable) {
            mutableStateOf(false)
        } else {
            mutableStateOf(true)
        }
    } // Expand State

    Card(
        shape = roundedCornerShape, // shape
        colors = CardDefaults.cardColors(cardColors), // color
        modifier = modifier
            .clickable(enabled = expendable) {
                addToFavorite()
                if (isAddToFavorite) return@clickable
                expanded = !expanded
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize() // edit animation here
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.5f)
                ) {
                    Column {
                        Text(
                            text = state.city,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = state.country,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Text(
                        text = state.temperature,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 120.dp, minHeight = 120.dp)
                        .fillMaxHeight()
                        .weight(0.5f)
                ) {
                    Image(
                        painter = painterResource(state.weatherType.iconRes),
                        contentDescription = "weather icon",
                        modifier = Modifier
                            .widthIn(min = 120.dp)
                            .fillMaxHeight(fraction = 0.8f)
                    )

                    Text(
                        text = state.weatherType.weatherDesc,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxHeight()
                    )
                }
            }
            if (expanded) {
                // TODO: Choose lazy or not
                Spacer(modifier = Modifier.height(16.dp))

                Divider(
                    modifier = Modifier.fillMaxWidth().height(1.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                ExpandedItems(
                    key = "Low/High",
                    value = state.lowHigh,
                    icon = SharedRes.images.highlow
                )
                Divider(
                    modifier = Modifier.fillMaxWidth().height(1.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                ExpandedItems(
                    key = "FeelsLike",
                    value = state.feelsLike,
                    icon = null,
                )
                Divider(
                    modifier = Modifier.fillMaxWidth().height(1.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                ExpandedItems(
                    key = "Humidity",
                    value = state.humidity,
                    icon = SharedRes.images.humidity
                )
                Divider(
                    modifier = Modifier.fillMaxWidth().height(1.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                ExpandedItems(
                    key = "Pressure",
                    value = state.pressure,
                    icon = SharedRes.images.pressure
                )
                Divider(
                    modifier = Modifier.fillMaxWidth().height(1.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                ExpandedItems(
                    key = "Visibility",
                    value = state.visibility,
                    icon = SharedRes.images.visibility
                )
                Divider(
                    modifier = Modifier.fillMaxWidth().height(1.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                ExpandedItems(
                    key = "Wind",
                    value = state.windSpeed,
                    icon = SharedRes.images.wind
                )
                Divider(
                    modifier = Modifier.fillMaxWidth().height(1.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Day Per 3 Hours",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.height(16.dp))

                LazyRow {
                    state.weatherFuture.forEach {
                        item {
                            DailyItems(
                                future = it
                            )

                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
        }
    }
}