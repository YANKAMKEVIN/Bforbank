package com.kevin.multiapiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kevin.multiapiapp.ui.MultiApiApp
import com.kevin.multiapiapp.ui.theme.MultiApiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiApiAppTheme {
                MultiApiApp()
            }
        }
    }
}