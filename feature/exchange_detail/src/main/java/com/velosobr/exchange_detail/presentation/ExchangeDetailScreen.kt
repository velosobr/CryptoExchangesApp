package com.velosobr.exchange_detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.core.state.UiState
import com.velosobr.designsystem.components.ErrorBox
import com.velosobr.designsystem.theme.DSColor
import com.velosobr.designsystem.theme.DSSpacing
import com.velosobr.exchange_detail.components.ExchangeHeader
import com.velosobr.exchange_detail.components.ExchangeInfoRow
import com.velosobr.exchange_detail.components.ExchangeSection
import com.velosobr.exchange_detail.model.ExchangeDetailModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ExchangeDetailScreen(
    exchangeId: String,
    iconUrl: String,
    onBackClick: () -> Unit,
    viewModel: ExchangeDetailViewModel = getViewModel { parametersOf(exchangeId, iconUrl) }
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.padding(32.dp))
        is UiState.Error -> ErrorBox(
            title = "Oops! Something went wrong.",
            message = (state as UiState.Error).message,
            image = Icons.Default.Clear,
            onRetry = { viewModel.fetchExchangeDetail() }
        )

        is UiState.Success -> {
            val model = (state as UiState.Success).data
            ExchangeDetailContent(model, iconUrl, onBackClick)
        }
    }
}

@Composable
private fun ExchangeDetailContent(
    model: ExchangeDetailModel,
    iconUrl: String,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DSColor.DarkBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        IconButton(onClick = onBackClick) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = DSColor.OnPrimary)
        }

        ExchangeHeader(model, iconUrl)

        Spacer(modifier = Modifier.height(24.dp))

        ExchangeSection(title = "General Information") {
            ExchangeInfoRow(label = "Name", value = model.name)
            ExchangeInfoRow(label = "Website", value = model.website)
            ExchangeInfoRow(label = "Data Start", value = model.dataTradeStart)
        }

        Spacer(modifier = Modifier.height(DSSpacing.lg))

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
    )
    MaterialTheme {
        ExchangeDetailContent(
            model = fakeModel,
            iconUrl = "https://cdn-icons-png.flaticon.com/512/36/36658.png",
            onBackClick = {})
    }
}