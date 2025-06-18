package com.velosobr.data.repository

import com.velosobr.data.local.ExchangeIconDataStore
import com.velosobr.data.remote.api.ExchangeApiService
import com.velosobr.domain.repository.ExchangeIconRepository
import timber.log.Timber

class ExchangeIconRepositoryImpl(
    private val apiKey: String,
    private val api: ExchangeApiService,
    private val dataStore: ExchangeIconDataStore
) : ExchangeIconRepository {

    override suspend fun getIconUrlByExchangeId(exchangeId: String): String? {
        val cached = dataStore.getIconUrl(exchangeId)
        if (cached.isNullOrBlank()) {
            Timber.w("⚠️ Ícone de $exchangeId não encontrado no cache.")
        }
        return cached
    }

    override suspend fun prefetchAllIcons() {
        try {
            Timber.d("🚀 Fazendo preload de todos os ícones...")
            val icons = api.getExchangeIcons(apiKey = apiKey, size = 64)
            icons.forEach { icon ->
                if (!icon.url.isNullOrBlank()) {
                    icon.exchangeId?.let { dataStore.saveIconUrl(it, icon.url) }
                }
            }
            Timber.d("✅ Preload de ícones concluído com ${icons.size} registros.")
        } catch (e: Exception) {
            Timber.e(e, "❌ Erro ao fazer preload de ícones")
        }    }
}
