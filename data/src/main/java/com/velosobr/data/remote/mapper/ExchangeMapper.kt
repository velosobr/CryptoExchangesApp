package com.velosobr.data.remote.mapper

import com.velosobr.data.remote.dto.ExchangeDto
import com.velosobr.domain.model.Exchange
import java.time.ZonedDateTime

fun ExchangeDto.toDomain(): Exchange {
    return Exchange(
        exchangeId = exchangeId,
        website = website,
        name = name,
        dataQuoteStart = ZonedDateTime.parse(dataQuoteStart),
        dataQuoteEnd = ZonedDateTime.parse(dataQuoteEnd),
        dataOrderbookStart = ZonedDateTime.parse(dataOrderbookStart),
        dataOrderbookEnd = ZonedDateTime.parse(dataOrderbookEnd),
        dataTradeStart = ZonedDateTime.parse(dataTradeStart),
        dataTradeEnd = ZonedDateTime.parse(dataTradeEnd),
        dataSymbolsCount = dataSymbolsCount,
        volume1hrsUsd = volume1HrsUsd,
        volume1dayUsd = volume1DayUsd,
        volume1mthUsd = volume1MthUsd,
        rank = rank
    )
}