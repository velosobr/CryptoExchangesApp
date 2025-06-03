package com.velosobr.data.repository

import com.velosobr.data.local.ExchangeIconDataStore
import com.velosobr.data.remote.api.ExchangeApiService
import com.velosobr.domain.repository.ExchangeIconRepository
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class ExchangeIconRepositoryImpl(
    private val apiKey: String,
    private val api: ExchangeApiService,
    private val dataStore: ExchangeIconDataStore
) : ExchangeIconRepository {

    override suspend fun getIconUrlByExchangeId(exchangeId: String): String? {
        Timber.d("🔍 Buscando ícone para exchangeId: $exchangeId")

        val cached = dataStore.getIconUrl(exchangeId)
        if (!cached.isNullOrBlank()) {
            Timber.d("✅ Ícone encontrado no cache para $exchangeId")
            return cached
        }

        Timber.d("📡 Ícone não está no cache, buscando via API...")

        return try {
            val icons = api.getExchangeIcons(apiKey = apiKey, size = 32)
            Timber.d("📦 Ícones recebidos da API: ${icons.size}")

            val match = icons.firstOrNull { it.exchangeId.equals(exchangeId, ignoreCase = true) }
            val url = match?.url
            if (!url.isNullOrBlank()) {
                Timber.d("💾 Salvando ícone no cache: $url")
                dataStore.saveIconUrl(exchangeId, url)
            } else {
                Timber.w("⚠️ Nenhum ícone encontrado na resposta da API para $exchangeId")
            }
            url
        } catch (e: IOException) {
            Timber.e("ExchangeIconRepo", "Network error fetching exchange icon", e)
            null
        } catch (e: HttpException) {
            Timber.e("ExchangeIconRepo", "API error fetching exchange icon", e)
            null
        } catch (e: Exception) {
            Timber.e(e, "❌ Erro ao buscar ícone da exchange via API")
            null
        }
    }
}
