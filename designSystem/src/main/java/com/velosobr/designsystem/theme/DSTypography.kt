package com.velosobr.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.velosobr.designsystem.R

val WorkSans = FontFamily(
    Font(R.font.work_sans_regular),
    Font(R.font.work_sans_bold, FontWeight.Bold),
)

val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = WorkSans,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = WorkSans,
        fontSize = 20.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = WorkSans,
        fontSize = 24.sp
    )
)