package com.velosobr.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExchangeDto(
    @Json(name = "exchange_id") val exchangeId: String,
    @Json(name = "website") val website: String,
    @Json(name = "name") val name: String,
    @Json(name = "data_quote_start") val dataQuoteStart: String,
    @Json(name = "data_quote_end") val dataQuoteEnd: String,
    @Json(name = "data_orderbook_start") val dataOrderbookStart: String,
    @Json(name = "data_orderbook_end") val dataOrderbookEnd: String,
    @Json(name = "data_trade_start") val dataTradeStart: String,
    @Json(name = "data_trade_end") val dataTradeEnd: String,
    @Json(name = "data_symbols_count") val dataSymbolsCount: Int,
    @Json(name = "volume_1hrs_usd") val volume1HrsUsd: Double,
    @Json(name = "volume_1day_usd") val volume1DayUsd: Double,
    @Json(name = "volume_1mth_usd") val volume1MthUsd: Double,
    @Json(name = "rank") val rank: Int
)