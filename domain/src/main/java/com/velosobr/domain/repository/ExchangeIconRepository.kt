package com.velosobr.domain.repository

interface ExchangeIconRepository {
    suspend fun getIconUrlByExchangeId(exchangeId: String): String?
    suspend fun prefetchAllIcons()
}