package com.velosobr.cryptoexchangesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.velosobr.cryptoexchangesapp.di.HelloService
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val helloService: HelloService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                Text(
                    text = helloService.getMessage(),
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp
                )
            }
        }
    }
}
