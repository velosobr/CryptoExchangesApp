package com.velosobr.exchange_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.designsystem.theme.AppTypography
import com.velosobr.designsystem.theme.DSColor

@Composable
fun ExchangeInfoRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .background(DSColor.DarkBackground)
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = AppTypography.labelSmall,
            color = DSColor.DarkText.copy(alpha = 0.7f)
        )
        Text(
            text = value,
            color = DSColor.DarkText,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExchangeInfoRow() {
    ExchangeInfoRow(label = "Volume 1 Day", value = "$1,234,567")
}