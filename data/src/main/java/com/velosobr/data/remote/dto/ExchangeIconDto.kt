package com.velosobr.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExchangeIconDto(
    @Json(name = "exchange_id") val exchangeId: String?,
    @Json(name = "asset_id") val assetId: String?,
    @Json(name = "url") val url: String?
)