package com.velosobr.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velosobr.designsystem.theme.AppTheme
import com.velosobr.designsystem.theme.AppTypography
import com.velosobr.designsystem.theme.DSColor

@Composable
fun ErrorBox(
    modifier: Modifier = Modifier,
    title: String = "Oops! Algo deu errado.",
    message: String,
    image: ImageVector? = null,
    onRetry: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DSColor.DarkBackground)
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        image?.let {
            Image(
                imageVector = it,
                contentDescription = null,
                colorFilter = DSColor.colorFilter,
                modifier = Modifier
                    .size(64.dp)
            )
        } ?: run {
            Spacer(modifier = Modifier.height(64.dp))
        }

        Text(
            text = title,
            color = DSColor.DarkText,
            style = AppTypography.titleLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = DSColor.DarkText,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRetry) {
            Text("Try Again", style = AppTypography.labelLarge, color = DSColor.OnPrimary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorBoxPreview() {
    AppTheme {
        ErrorBox(
            title = "Erro de Conexão",
            message = "Não foi possível conectar ao servidor. Verifique sua conexão com a internet e tente novamente.",
            image = Icons.Default.Clear,
            onRetry = {}
        )
    }
}
