package com.velosobr.domain.model

import java.time.ZonedDateTime

data class Exchange(
    val exchangeId: String,
    val website: String,
    val name: String,
    val dataQuoteStart: ZonedDateTime,
    val dataQuoteEnd: ZonedDateTime,
    val dataOrderbookStart: ZonedDateTime,
    val dataOrderbookEnd: ZonedDateTime,
    val dataTradeStart: ZonedDateTime,
    val dataTradeEnd: ZonedDateTime,
    val dataSymbolsCount: Int,
    val volume1hrsUsd: Double,
    val volume1dayUsd: Double,
    val volume1mthUsd: Double,
    val rank: Int,
    val iconUrl: String
)