package com.velosobr.data.remote.api

import com.velosobr.data.remote.dto.ExchangeDto
import retrofit2.http.GET
import retrofit2.http.Header

interface CoinApiService {
    @GET("v1/exchanges")
    suspend fun getExchanges(@Header("X-CoinAPI-Key") apiKey: String): List<ExchangeDto>
}