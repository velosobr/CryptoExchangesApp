package com.velosobr.data.remote.api

import com.velosobr.data.remote.dto.ExchangeDto
import com.velosobr.data.remote.dto.ExchangeIconDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ExchangeApiService {
    @GET("v1/exchanges")
    suspend fun getExchanges(@Header("X-CoinAPI-Key") apiKey: String): List<ExchangeDto>

    @GET("v1/exchanges/{exchange_id}")
    suspend fun getExchangeById(
        @Path("exchange_id") id: String,
        @Query("apikey") apiKey: String
    ): List<ExchangeDto>

    @GET("v1/exchanges/icons/{size}")
    suspend fun getExchangeIcons(
        @Path("size") size: Int = 32,
        @Header("X-CoinAPI-Key") apiKey: String
    ): List<ExchangeIconDto>
}