package com.velosobr.domain.model

import java.time.ZonedDateTime

data class Exchange(
    val exchangeId: String,
    val website: String,
    val name: String,
    val dataQuoteStart: ZonedDateTime? = null,
    val dataQuoteEnd: ZonedDateTime? = null,
    val dataOrderbookStart: ZonedDateTime? = null,
    val dataOrderbookEnd: ZonedDateTime? = null,
    val dataTradeStart: ZonedDateTime? = null,
    val dataTradeEnd: ZonedDateTime? = null,
    val dataSymbolsCount: Int? = null,
    val volume1hrsUsd: Double? = null,
    val volume1dayUsd: Double? = null,
    val volume1mthUsd: Double? = null,
    val rank: Int? = null
)