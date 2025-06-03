package com.velosobr.data.repository

import com.velosobr.core.error.AppException
import com.velosobr.core.result.ExchangeResult
import com.velosobr.data.remote.api.ExchangeApiService
import com.velosobr.data.remote.mapper.toDomain
import com.velosobr.domain.model.Exchange
import com.velosobr.domain.repository.ExchangeRepository
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class ExchangeRepositoryImpl(
    private val api: ExchangeApiService,
    private val apiKey: String
) : ExchangeRepository {

    override suspend fun getExchanges(): ExchangeResult<List<Exchange>> {
        return try {
            val exchanges = api.getExchanges(apiKey).map { it.toDomain() }
            Timber.d("Fetched ${exchanges.size} exchanges")
            ExchangeResult.Success(exchanges)
        } catch (e: IOException) {
            ExchangeResult.Error(AppException.Network)
        } catch (e: HttpException) {
            Timber.e(e, "Failed to fetch exchanges")
            ExchangeResult.Error(AppException.Api(code = e.code(), message = e.message()))
        } catch (e: Exception) {
            Timber.e(e, "Unknown error while fetching exchanges")
            ExchangeResult.Error(AppException.Unknown(e))
        }
    }

    override suspend fun getExchangeById(id: String): ExchangeResult<Exchange> {
        return try {
            val response = api.getExchangeById(id, apiKey)
            ExchangeResult.Success(
                response.firstOrNull()?.toDomain()
                    ?: throw AppException.NotFound("Exchange with ID $id not found")
            )
        } catch (e: IOException) {
            Timber.e(e, "Network error while fetching exchange by ID: $id")
            ExchangeResult.Error(AppException.Network)
        } catch (e: HttpException) {
            Timber.e(e, "API error while fetching exchange by ID: $id")
            ExchangeResult.Error(AppException.Api(code = e.code(), message = e.message()))
        } catch (e: Exception) {
            Timber.e(e, "Unknown error while fetching exchange by ID: $id")
            ExchangeResult.Error(AppException.Unknown(e))
        }
    }
}