package com.velosobr.cryptoexchangesapp.presentation.navigation


import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.velosobr.cryptoexchangesapp.data.BuildConfig
import com.velosobr.designsystem.theme.AppTheme
import com.velosobr.exchange_detail.presentation.ExchangeDetailScreen
import com.velosobr.exchange_list.presentation.ExchangeListScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExchangesApp() {
    val navController = rememberAnimatedNavController() // precisa importar do accompanist

    AppTheme {
        AnimatedNavHost(
            navController = navController,
            startDestination = "exchangeList"
        ) {
            composable(
                "exchangeList",
                enterTransition = { fadeIn(animationSpec = tween(300)) },
                exitTransition = { fadeOut(animationSpec = tween(300)) },
                popEnterTransition = { fadeIn(animationSpec = tween(300)) },
                popExitTransition = { fadeOut(animationSpec = tween(300)) }
            ) {
                ExchangeListScreen(
                    onExchangeClick = { exchangeId, iconUrl ->
                        val validIconUrl = iconUrl.ifEmpty { BuildConfig.DEFAULT_ICON_URL }
                        val encodedIconUrl = Uri.encode(validIconUrl)
                        navController.navigate("exchangeDetail/$exchangeId/$encodedIconUrl")
                    }
                )
            }

            composable(
                route = "exchangeDetail/{exchangeId}/{iconUrl}",
                arguments = listOf(
                    navArgument("exchangeId") { type = NavType.StringType },
                    navArgument("iconUrl") { type = NavType.StringType }
                ),
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(300)
                    ) + fadeIn(animationSpec = tween(300))
                },
                exitTransition = {
                    slideOutHorizontally(targetOffsetX = { -300 }, animationSpec = tween(300))
                                 },
                popEnterTransition = { fadeIn(animationSpec = tween(300)) },
                popExitTransition = { fadeOut(animationSpec = tween(300)) }
            ) { backStackEntry ->
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