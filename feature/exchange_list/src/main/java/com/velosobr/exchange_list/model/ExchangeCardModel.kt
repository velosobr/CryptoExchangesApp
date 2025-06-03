package com.velosobr.exchange_list.model

import com.velosobr.core.utils.formatVolume
import com.velosobr.domain.model.Exchange

data class ExchangeCardModel(
    val name: String,
    val id: String,
    val volume: String,
)

fun Exchange.toCardModel(): ExchangeCardModel {
    return ExchangeCardModel(
        name = name,
        id = exchangeId,
        volume = formatVolume(volume1dayUsd),
    )
}