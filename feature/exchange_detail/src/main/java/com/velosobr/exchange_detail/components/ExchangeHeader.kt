package com.velosobr.exchange_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.velosobr.exchange_detail.model.ExchangeDetailModel

@Composable
fun ExchangeHeader(model: ExchangeDetailModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = model.iconUrl,
            contentDescription = model.name,
            modifier = Modifier
                .size(120.dp)
        )
        Text(
            text = model.name,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Exchange ID: ${model.exchangeId}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExchangeHeader() {
    ExchangeHeader(
        ExchangeDetailModel(
            exchangeId = "BINANCE",
            name = "Binance",
            website = "https://binance.com",
            dataTradeStart= "2017-01-01",
            volume1dayUsd = "$1,000,000",
            volume1hrsUsd = "$100,000",
            activePairs = 434,
            iconUrl = "https://cryptoicons.org/api/icon/binance/200"
        )
    )
}