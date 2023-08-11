package net.yuuzu.weatherapp.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.fontFamilyResource
import net.yuuzu.weatherapp.SharedRes
import net.yuuzu.weatherapp.ui.theme.DarkColors
import net.yuuzu.weatherapp.ui.theme.LightColors

//actual fun WeatherTheme(
//    darkTheme: Boolean,
//    dynamicColor: Boolean,
////    content: @Composable () -> Unit
//) {}


@Composable
actual fun WeatherTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    val typography = Typography(
        titleLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        ),

        titleMedium = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
        ),

        titleSmall = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        ),
        bodySmall = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
        ),
    )

    MaterialTheme(
        colorScheme = if(darkTheme) DarkColors else LightColors,
        typography = typography,
        content = content
    )
}