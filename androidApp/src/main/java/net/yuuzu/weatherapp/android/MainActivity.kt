package net.yuuzu.weatherapp.android

import android.os.Bundle
import androidx.compose.ui.graphics.Color
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent
import net.yuuzu.weatherapp.MainView

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }
}
