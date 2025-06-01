package com.velosobr.cryptoexchangesapp.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.velosobr.designsystem.theme.AppTheme
import com.velosobr.exchange_detail.presentation.ExchangeDetailScreen
import com.velosobr.exchange_list.presentation.ExchangeListScreen

@Composable
fun ExchangesApp() {
    val navController = rememberNavController()

    AppTheme {
        NavHost(
            navController = navController,
            startDestination = "exchangeList"
        ) {
            composable("exchangeList") {
                ExchangeListScreen(
                    onExchangeClick = { exchangeId ->
                        navController.navigate("exchangeDetail/$exchangeId")
                    }
                )
            }

            composable("exchangeDetail/{exchangeId}") { backStackEntry ->
                val exchangeId = backStackEntry.arguments?.getString("exchangeId") ?: return@composable
                ExchangeDetailScreen(
                    exchangeId = exchangeId,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}