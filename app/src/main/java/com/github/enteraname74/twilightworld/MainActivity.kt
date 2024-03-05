package com.github.enteraname74.twilightworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.enteraname74.twilightworld.screen.MainScreen
import com.github.enteraname74.twilightworld.ui.theme.TwilightWorldTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwilightWorldTheme {
                MainScreen(viewModel = koinViewModel())
            }
        }
    }
}