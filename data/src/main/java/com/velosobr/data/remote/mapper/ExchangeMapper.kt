package com.velosobr.data.remote.mapper

import com.velosobr.data.remote.dto.ExchangeDto
import com.velosobr.domain.model.Exchange
import java.time.ZonedDateTime

internal fun ExchangeDto.toDomain(): Exchange {
    return Exchange(
        exchangeId = exchangeId ?: "unknown",
        website = website ?: "N/A",
        name = name ?: "Unnamed",
        dataQuoteStart = dataQuoteStart?.let { ZonedDateTime.parse(it) } ?: ZonedDateTime.now(),
        dataQuoteEnd = dataQuoteEnd?.let { ZonedDateTime.parse(it) } ?: ZonedDateTime.now(),
        dataOrderbookStart = dataOrderbookStart?.let { ZonedDateTime.parse(it) } ?: ZonedDateTime.now(),
        dataOrderbookEnd = dataOrderbookEnd?.let { ZonedDateTime.parse(it) } ?: ZonedDateTime.now(),
        dataTradeStart = dataTradeStart?.let { ZonedDateTime.parse(it) } ?: ZonedDateTime.now(),
        dataTradeEnd = dataTradeEnd?.let { ZonedDateTime.parse(it) } ?: ZonedDateTime.now(),
        dataSymbolsCount = dataSymbolsCount ?: 0,
        volume1hrsUsd = volume1HrsUsd ?: 0.0,
        volume1dayUsd = volume1DayUsd ?: 0.0,
        volume1mthUsd = volume1MthUsd ?: 0.0,
        rank = rank ?: 0.0,
    )
}