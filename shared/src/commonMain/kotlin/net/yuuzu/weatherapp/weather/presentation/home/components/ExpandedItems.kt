package net.yuuzu.weatherapp.weather.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HotTub
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource
import net.yuuzu.weatherapp.SharedRes

@Composable
fun ExpandedItems(
    key: String,
    value: String,
    icon: ImageResource?,
    iconSize: Int = 24,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        Row {
            if (icon == null) {
                Icon(
                    imageVector = Icons.Filled.HotTub,
                    contentDescription = key,
                    modifier = Modifier.size(width = iconSize.dp, height = iconSize.dp)
                )
            } else {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = key,
                    modifier = Modifier.size(width = iconSize.dp, height = iconSize.dp)
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = key,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 1.dp)
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 1.dp)
        )
    }
}