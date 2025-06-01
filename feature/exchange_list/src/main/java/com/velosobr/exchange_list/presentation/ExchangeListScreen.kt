package com.velosobr.exchange_list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velosobr.core.state.UiState
import com.velosobr.designsystem.components.ExchangeCard
import com.velosobr.designsystem.theme.AppTheme
import com.velosobr.domain.model.Exchange
import com.velosobr.exchange_list.model.toCardModel

@Composable
fun ExchangeListScreen(
    viewModel: ExchangeListViewModel,
    onExchangeClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ExchangeListContent(uiState = uiState, onExchangeClick = onExchangeClick)
}

@Composable
fun ExchangeListContent(
    uiState: UiState<List<Exchange>>,
    onExchangeClick: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is UiState.Success -> {
                val exchanges = uiState.data
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(items = exchanges, key = { it.exchangeId }) { exchange ->
                        val model = exchange.toCardModel()
                        ExchangeCard(
                            name = model.name,
                            id = model.id,
                            volume = model.volume,
                            iconUrl = model.iconUrl,
                            onClick = { onExchangeClick(model.id) },
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            is UiState.Error -> {
                Text(
                    text = "Erro ao carregar exchanges: ${uiState.message}",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExchangeListContentPreview() {
    val fakeData = listOf(
        Exchange(
            exchangeId = "binance",
            website = "https://www.binance.com",
            name = "Binance",
            dataQuoteStart = java.time.ZonedDateTime.now(),
            dataQuoteEnd = java.time.ZonedDateTime.now(),
            dataOrderbookStart = java.time.ZonedDateTime.now(),
            dataOrderbookEnd = java.time.ZonedDateTime.now(),
            dataTradeStart = java.time.ZonedDateTime.now(),
            dataTradeEnd = java.time.ZonedDateTime.now(),
            dataSymbolsCount = 100,
            volume1hrsUsd = 100000.0,
            volume1dayUsd = 5000000.0,
            volume1mthUsd = 150000000.0,
            rank = 1
        ),
        Exchange(
            exchangeId = "coinbase",
            website = "https://www.coinbase.com",
            name = "Coinbase",
            dataQuoteStart = java.time.ZonedDateTime.now(),
            dataQuoteEnd = java.time.ZonedDateTime.now(),
            dataOrderbookStart = java.time.ZonedDateTime.now(),
            dataOrderbookEnd = java.time.ZonedDateTime.now(),
            dataTradeStart = java.time.ZonedDateTime.now(),
            dataTradeEnd = java.time.ZonedDateTime.now(),
            dataSymbolsCount = 80,
            volume1hrsUsd = 50000.0,
            volume1dayUsd = 2000000.0,
            volume1mthUsd = 60000000.0,
            rank = 2
        ),
        Exchange(
            exchangeId = "kraken",
            website = "https://www.kraken.com",
            name = "Kraken",
            dataQuoteStart = java.time.ZonedDateTime.now(),
            dataQuoteEnd = java.time.ZonedDateTime.now(),
            dataOrderbookStart = java.time.ZonedDateTime.now(),
            dataOrderbookEnd = java.time.ZonedDateTime.now(),
            dataTradeStart = java.time.ZonedDateTime.now(),
            dataTradeEnd = java.time.ZonedDateTime.now(),
            dataSymbolsCount = 60,
            volume1hrsUsd = 30000.0,
            volume1dayUsd = 1000000.0,
            volume1mthUsd = 30000000.0,
            rank = 3
        )
    )
    AppTheme {
        ExchangeListContent(
            uiState = UiState.Success(fakeData),
            onExchangeClick = {}
        )
    }

}