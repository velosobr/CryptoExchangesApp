package com.velosobr.designsystem.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter

object DSColor {
    val Primary = Color(0xFF4E44CE)
    val Secondary = Color(0xFF03DAC5)
    val Background = Color(0xFFF7F7F7)

    val DarkBackground = Color(0xFF1E1E1E)
    val DarkSurface = Color(0xFF2A2A2A)
    val DarkPrimary = Color(0xFF4E44CE)
    val DarkText = Color(0xFFEDEDED)
    val colorFilter = ColorFilter.tint(DarkPrimary)

    val OnPrimary = Color(0xFFFFFFFF)
    val ErrorColor = Color(0xFFCF6679)
    val OnError = Color(0xFFFFFFFF)
}
