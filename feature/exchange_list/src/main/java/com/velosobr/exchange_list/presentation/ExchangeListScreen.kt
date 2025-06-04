package com.velosobr.exchange_list.presentation

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
import com.velosobr.core.state.UiState
import com.velosobr.cryptoexchangesapp.data.BuildConfig
import com.velosobr.designsystem.components.DSExchangeCardComponent
import com.velosobr.designsystem.components.ErrorBox
import com.velosobr.designsystem.theme.DSAppTypography
import com.velosobr.designsystem.theme.DSColor
import com.velosobr.designsystem.theme.DSSpacing
import com.velosobr.domain.model.Exchange
import com.velosobr.exchange_list.model.toCardModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangeListScreen(
    onExchangeClick: (String, String) -> Unit
) {
    val viewModel: ExchangeListViewModel = koinViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val iconMap by viewModel.exchangeIcons.collectAsStateWithLifecycle()

    ExchangeListContent(
        uiState = uiState,
        iconMap = iconMap,
        onExchangeClick = onExchangeClick,
        onRetryClick = {
            viewModel.fetchExchanges()
        })
}

@Composable
fun ExchangeListContent(
    uiState: UiState<List<Exchange>>,
    iconMap: Map<String, String?>,
    onExchangeClick: (String, String) -> Unit,
    onRetryClick: () -> Unit
) {
    Scaffold(topBar = {
        Text(
            text = "Exchanges List",
            style = DSAppTypography.titleLarge,
            color = DSColor.DarkText,
            modifier = Modifier
                .padding(horizontal = DSSpacing.md, vertical = DSSpacing.lg)
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(DSColor.DarkBackground)
                .fillMaxSize()
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is UiState.Success -> {
                    val exchanges = uiState.data
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = DSSpacing.xxxl)
                    ) {
                        items(items = exchanges, key = { it.exchangeId }) { exchange ->
                            val iconUrl = iconMap[exchange.exchangeId] ?: BuildConfig.DEFAULT_ICON_URL
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
                                        fadeInSpec = androidx.compose.animation.core.tween(300),
                                        fadeOutSpec = androidx.compose.animation.core.tween(300),
                                    )
                            )

                        }
                    }
                }

                is UiState.Error -> {
                    ErrorBox(
                        title = "Oops! Something went wrong.",
                        message = uiState.message,
                        onRetry = onRetryClick
                    )
                }
            }
        }
    }
}