package com.velosobr.domain.repository

import com.velosobr.core.result.ExchangeResult

import com.velosobr.domain.model.Exchange

interface ExchangeRepository {
    suspend fun getExchanges(): ExchangeResult<List<Exchange>>
    suspend fun getExchangeById(id: String): ExchangeResult<Exchange?>
}