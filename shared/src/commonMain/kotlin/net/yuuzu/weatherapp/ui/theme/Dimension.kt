package net.yuuzu.weatherapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions {
    val radiusSmall: Dp = 4.dp
    val radiusMedium: Dp = 8.dp
    val radiusLarge: Dp = 12.dp
    val spacingExtraSmall: Dp = 2.dp
    val spacingSmall: Dp = 4.dp
    val spacingMedium: Dp = 8.dp
    val spacingLarge: Dp = 12.dp
    val spacingExtraLarge: Dp = 24.dp
}

private val LocalMaterialDimensions = staticCompositionLocalOf { Dimensions() }

val MaterialTheme.dimensions: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalMaterialDimensions.current
