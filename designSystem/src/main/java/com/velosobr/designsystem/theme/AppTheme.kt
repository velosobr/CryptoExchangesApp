package com.velosobr.designsystem.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.velosobr.designsystem.theme.DSColor.DarkBackground
import com.velosobr.designsystem.theme.DSColor.DarkPrimary
import com.velosobr.designsystem.theme.DSColor.DarkSurface
import com.velosobr.designsystem.theme.DSColor.DarkText
import com.velosobr.designsystem.theme.DSColor.ErrorColor
import com.velosobr.designsystem.theme.DSColor.OnError
import com.velosobr.designsystem.theme.DSColor.OnPrimary

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = OnPrimary,
    onBackground = DarkText,
    onSurface = DarkText,
    error = ErrorColor,
    onError = OnError
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = AppTypography,
        content = content
    )
}