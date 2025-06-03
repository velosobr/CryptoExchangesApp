package com.velosobr.exchange_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.velosobr.designsystem.theme.DSAppTypography
import com.velosobr.designsystem.theme.DSColor
import com.velosobr.designsystem.theme.DSSpacing

@Composable
fun ExchangeInfoRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .background(DSColor.DarkBackground)
            .padding(vertical = DSSpacing.xs)
    ) {
        Text(
            text = label,
            style = DSAppTypography.labelSmall,
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