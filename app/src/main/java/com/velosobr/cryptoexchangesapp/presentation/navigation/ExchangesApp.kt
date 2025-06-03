package com.velosobr.cryptoexchangesapp.presentation.navigation


import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.velosobr.cryptoexchangesapp.data.BuildConfig
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
                    onExchangeClick = { exchangeId, iconUrl ->
                        val validIconUrl = iconUrl.ifEmpty { BuildConfig.DEFAULT_ICON_URL }
                        val encodedIconUrl = Uri.encode(validIconUrl)
                        navController.navigate("exchangeDetail/$exchangeId/$encodedIconUrl")
                    }
                )
            }

            composable("exchangeDetail/{exchangeId}/{iconUrl}") { backStackEntry ->
                val exchangeId =
                    backStackEntry.arguments?.getString("exchangeId") ?: return@composable
                val encodedIconUrl = backStackEntry.arguments?.getString("iconUrl") ?: ""
                val iconUrl = Uri.decode(encodedIconUrl)

                ExchangeDetailScreen(
                    exchangeId = exchangeId,
                    onBackClick = { navController.popBackStack() },
                    iconUrl = iconUrl,
                )
            }
        }
    }
}