package com.velosobr.exchange_detail.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.core.state.UiState
import com.velosobr.designsystem.components.ErrorBox
import com.velosobr.exchange_detail.components.ExchangeHeader
import com.velosobr.exchange_detail.components.ExchangeInfoRow
import com.velosobr.exchange_detail.components.ExchangeSection
import com.velosobr.exchange_detail.model.ExchangeDetailModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ExchangeDetailScreen(
    exchangeId: String,
    onBackClick: () -> Unit,
    viewModel: ExchangeDetailViewModel = getViewModel { parametersOf(exchangeId) }
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.padding(32.dp))
        is UiState.Error -> ErrorBox(
            title = "Oops! Something went wrong.",
            message = (state as UiState.Error).message,
            onRetry = { viewModel.fetchExchangeDetail() }
        )
        is UiState.Success -> {
            val model = (state as UiState.Success).data
            ExchangeDetailContent(model, onBackClick)
        }
    }
}

@Composable
private fun ExchangeDetailContent(model: ExchangeDetailModel, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        IconButton(onClick = onBackClick) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }

        ExchangeHeader(model)

        Spacer(modifier = Modifier.height(24.dp))

        ExchangeSection(title = "General Information") {
            ExchangeInfoRow(label = "Name", value = model.name)
            ExchangeInfoRow(label = "Website", value = model.website)
            ExchangeInfoRow(label = "Data Start", value = model.dataTradeStart)
        }

        Spacer(modifier = Modifier.height(24.dp))

        ExchangeSection(title = "Trading Information") {
            ExchangeInfoRow(label = "Volume 1 Day", value = model.volume1dayUsd)
            ExchangeInfoRow(label = "Volume 1 Hour", value = model.volume1hrsUsd)
            ExchangeInfoRow(label = "Active Pairs", value = model.activePairs.toString())
        }
    }
}

@Preview
@Composable
fun ExchangeDetailContentPreview() {
    val fakeModel = ExchangeDetailModel(
        name = "Fake Exchange",
        exchangeId = "fake-exchange",
        website = "https://fakeexchange.com",
        dataTradeStart = "2023-01-01",
        volume1dayUsd = "$1,000,000",
        volume1hrsUsd = "$100,000",
        activePairs = 50,
        iconUrl = "https://cryptoicons.org/api/icon/fake-exchange/200"
    )
    MaterialTheme {
        ExchangeDetailContent(model = fakeModel, onBackClick = {})
    }
}