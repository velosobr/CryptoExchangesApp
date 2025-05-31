package com.velosobr.domain.usecase

import com.velosobr.core.result.ExchangeResult
import com.velosobr.domain.model.Exchange
import com.velosobr.domain.repository.ExchangeRepository

class GetExchangesUseCase(
    private val repository: ExchangeRepository
) {
    suspend operator fun invoke(): ExchangeResult<List<Exchange>> {
        return repository.getExchanges()
    }
}