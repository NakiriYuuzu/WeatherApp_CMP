package net.yuuzu.weatherapp.core.presentation

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import dev.icerock.moko.resources.compose.fontFamilyResource
import net.yuuzu.weatherapp.SharedRes
import net.yuuzu.weatherapp.ui.theme.DarkColors
import net.yuuzu.weatherapp.ui.theme.LightColors

@Composable
actual fun WeatherTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if(darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    val view = LocalView.current
    if(!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(
                window,
                view
            ).isAppearanceLightStatusBars = darkTheme
        }
    }

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
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )

//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = typography,
//        content = {}
//    )
}