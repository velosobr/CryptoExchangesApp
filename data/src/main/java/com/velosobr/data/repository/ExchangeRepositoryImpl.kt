package com.velosobr.data.repository

import com.velosobr.core.error.AppException
import com.velosobr.core.result.ExchangeResult
import com.velosobr.data.remote.api.CoinApiService
import com.velosobr.data.remote.mapper.toDomain
import com.velosobr.domain.model.Exchange
import com.velosobr.domain.repository.ExchangeRepository

class ExchangeRepositoryImpl(
    private val api: CoinApiService,
    private val apiKey: String
) : ExchangeRepository {

    override suspend fun getExchanges(): ExchangeResult<List<Exchange>> {
        return try {
            val exchanges = api.getExchanges(apiKey).map { it.toDomain() }
            ExchangeResult.Success(exchanges)
        } catch (e: Exception) {
            ExchangeResult.Error(AppException.Unknown(e))
        }
    }

    override suspend fun getExchangeById(id: String): ExchangeResult<Exchange?> {
        TODO("Not yet implemented")
    }
}