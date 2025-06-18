package com.velosobr.exchange_list.presentation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.velosobr.cryptoexchangesapp.data.BuildConfig
import com.velosobr.designsystem.components.DSExchangeCardComponent
import com.velosobr.designsystem.components.ErrorBox
import com.velosobr.designsystem.theme.DSAppTypography
import com.velosobr.designsystem.theme.DSColor
import com.velosobr.designsystem.theme.DSSpacing
import com.velosobr.exchange_list.model.toCardModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangeListScreen(
    onExchangeClick: (String, String) -> Unit
) {
    val viewModel: ExchangeListViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ExchangeListContent(
        state = state,
        onExchangeClick = onExchangeClick,
        onRetryClick = { viewModel.fetchExchanges() },
        onRefresh = { viewModel.fetchExchanges() }
    )
}

@Composable
fun ExchangeListContent(
    state: ExchangeListState,
    onExchangeClick: (String, String) -> Unit,
    onRefresh: () -> Unit,
    onRetryClick: () -> Unit
) {

    val swipeRefreshState = rememberSwipeRefreshState(state.isRefreshing)

    Scaffold(
        topBar = {
            Text(
                text = "Exchanges List",
                style = DSAppTypography.titleLarge,
                color = DSColor.DarkText,
                modifier = Modifier
                    .padding(horizontal = DSSpacing.md, vertical = DSSpacing.lg)
            )
        }
    ) { paddingValues ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = onRefresh,
            indicatorPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(DSColor.DarkBackground)
                    .fillMaxSize()
            ) {
                when {
                    state.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    state.error != null -> {
                        ErrorBox(
                            title = "Oops! Something went wrong.",
                            message = state.error,
                            onRetry = onRetryClick
                        )
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(bottom = DSSpacing.xxxl)
                        ) {
                            items(state.exchanges, key = { it.exchangeId }) { exchange ->
                                val iconUrl = state.exchangeIcons[exchange.exchangeId]
                                    ?: BuildConfig.DEFAULT_ICON_URL
                                val model = exchange.toCardModel()

                                DSExchangeCardComponent(
                                    name = model.name,
                                    id = model.id,
                                    volume = model.volume,
                                    iconUrl = iconUrl,
                                    onClick = { onExchangeClick(model.id, iconUrl) },
                                    modifier = Modifier
                                        .padding(horizontal = DSSpacing.md)
                                        .animateItem(
                                            fadeInSpec = tween(300),
                                            fadeOutSpec = tween(300),
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}