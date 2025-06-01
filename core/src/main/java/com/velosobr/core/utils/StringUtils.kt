package com.velosobr.core.utils

fun formatVolume(volume: Double): String {
    return when {
        volume >= 1_000_000 -> "${(volume / 1_000_000).toInt()}M"
        volume >= 1_000 -> "${(volume / 1_000).toInt()}K"
        else -> volume.toInt().toString()
    }
}