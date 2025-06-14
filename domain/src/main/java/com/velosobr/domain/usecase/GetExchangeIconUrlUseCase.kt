package com.velosobr.domain.usecase

import com.velosobr.domain.repository.ExchangeIconRepository

class GetExchangeIconUrlUseCase(
    private val repository: ExchangeIconRepository
) {
    suspend operator fun invoke(exchangeId: String): String? {
        return repository.getIconUrlByExchangeId(exchangeId)
    }
}