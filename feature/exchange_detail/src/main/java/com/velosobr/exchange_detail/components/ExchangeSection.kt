package com.velosobr.exchange_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.designsystem.theme.DSAppTypography
import com.velosobr.designsystem.theme.DSColor

@Composable
fun ExchangeSection(
    title: String, content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = DSAppTypography.titleMedium,
            color = DSColor.DarkText,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
@Preview
fun PreviewExchangeSection() {
    ExchangeSection(title = "Sample Section") {
        Text(
            text = "This is a sample content inside the section.",
            style = MaterialTheme.typography.bodyMedium,
            color = DSColor.DarkText,
            modifier = Modifier.padding(8.dp)
        )
    }
}