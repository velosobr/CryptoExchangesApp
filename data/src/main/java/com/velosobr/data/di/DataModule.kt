package com.velosobr.data.di

import com.velosobr.cryptoexchangesapp.data.BuildConfig
import com.velosobr.data.remote.api.ExchangeApiService
import com.velosobr.data.remote.interceptor.LoggingInterceptor
import com.velosobr.domain.usecase.GetExchangeIconUrlUseCase
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {
    factory {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(LoggingInterceptor())
            }
        }.build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://rest.coinapi.io/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(ExchangeApiService::class.java)
    }

    single { GetExchangeIconUrlUseCase(get()) }
}