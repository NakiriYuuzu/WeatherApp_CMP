package net.yuuzu.weatherapp.weather.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import net.yuuzu.weatherapp.weather.domain.models.WeatherFuture

@Composable
fun DailyItems(
    future: WeatherFuture,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    cardColors: Color = MaterialTheme.colorScheme.tertiary,
    modifier: Modifier = Modifier
) {
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(cardColors.copy(alpha = 0.10f)),
        modifier = modifier
            .height(IntrinsicSize.Max)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = future.temperature,
                style = MaterialTheme.typography.bodyLarge,
            )
            Image(
                painter = painterResource(future.weatherType.iconRes),
                contentDescription = future.weatherType.weatherDesc,
                modifier = Modifier.size(50.dp, 50.dp)
                    .height(50.dp)
            )
            Text(
                text = future.dateTime.substring(5, 16),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}