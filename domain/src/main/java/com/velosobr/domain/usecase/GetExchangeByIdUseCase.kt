package com.velosobr.domain.usecase

import com.velosobr.core.result.ExchangeResult
import com.velosobr.domain.model.Exchange
import com.velosobr.domain.repository.ExchangeRepository

class GetExchangeByIdUseCase(
    private val repository: ExchangeRepository
) {
    suspend operator fun invoke(id: String): ExchangeResult<Exchange?> {
        return repository.getExchangeById(id)
    }
}