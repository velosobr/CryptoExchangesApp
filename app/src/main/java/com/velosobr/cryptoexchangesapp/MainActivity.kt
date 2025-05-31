package com.velosobr.cryptoexchangesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.velosobr.cryptoexchangesapp.di.HelloService
import com.velosobr.designsystem.theme.AppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val helloService: HelloService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = helloService.getMessage(),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(16.dp),
                    )
                }
            }
        }
    }
}
