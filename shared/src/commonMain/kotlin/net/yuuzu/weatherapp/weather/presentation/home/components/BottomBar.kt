package net.yuuzu.weatherapp.weather.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.safeDrawingPadding
import moe.tlaster.precompose.navigation.Navigator
import net.yuuzu.weatherapp.util.navigation.Screen
import net.yuuzu.weatherapp.util.navigation.currentRoute

@OptIn(ExperimentalSoftwareKeyboardApi::class)
@Composable
fun CustomBottomBar(
    navigator: Navigator,
    elevation: Float = 0f,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = elevation.dp,
        modifier = modifier
            .absoluteOffset(y = (0).dp)
            .safeDrawingPadding()
    ) {
        val bottomNavItem = listOf(
            Screen.HomeScreen,
            Screen.FavoriteScreen,
            Screen.SearchScreen,
        )

        bottomNavItem.forEach {
            if (it.title != null && it.navIcon != null) {
                val isSelected = currentRoute(navigator) == it.route
                BottomBarItem(
                    text = it.title,
                    isSelected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            navigator.navigate(it.route)
                        }
                    },
                    iconRes = it.navIcon,
                    modifier = Modifier.weight(1f / bottomNavItem.size)
                )
            }
        }
    }
}

@Composable
fun BottomBarItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    iconRes: ImageVector,
    modifier: Modifier = Modifier,
) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = onClick,
//            colors = ButtonDefaults.buttonColors(
//                containerColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.primaryContainer,
//                contentColor = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onTertiary
//            ),
            elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Icon(imageVector = iconRes, contentDescription = null)
        }

        if (isSelected) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}