package com.velosobr.cryptoexchangesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.velosobr.cryptoexchangesapp.presentation.navigation.ExchangesApp
import com.velosobr.designsystem.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
               ExchangesApp()
            }
        }
    }
}
