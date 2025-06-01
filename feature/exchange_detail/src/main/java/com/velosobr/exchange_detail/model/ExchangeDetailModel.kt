package com.velosobr.exchange_detail.model

import com.velosobr.core.utils.formatVolume
import com.velosobr.domain.model.Exchange

data class ExchangeDetailModel(
    val name: String,
    val exchangeId: String,
    val website: String,
    val dataTradeStart: String,
    val volume1dayUsd: String,
    val volume1hrsUsd: String,
    val activePairs: Int,
    val iconUrl: String
)

fun Exchange.toDetailModel(): ExchangeDetailModel {
    return ExchangeDetailModel(
        name = name,
        exchangeId = exchangeId,
        website = website,
        dataTradeStart = dataTradeStart.toLocalDate().toString(),
        volume1dayUsd = formatVolume(volume1dayUsd),
        volume1hrsUsd = formatVolume(volume1hrsUsd),
        activePairs = dataSymbolsCount,
        iconUrl = "https://cryptoicons.org/api/icon/${exchangeId.lowercase()}/200"
    )

}