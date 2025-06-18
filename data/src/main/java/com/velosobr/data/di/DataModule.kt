package com.velosobr.data.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.velosobr.cryptoexchangesapp.data.BuildConfig
import com.velosobr.data.remote.api.ExchangeApiService
import com.velosobr.data.remote.interceptor.LoggingInterceptor
import com.velosobr.domain.usecase.GetExchangeIconUrlByIdUseCase
import com.velosobr.domain.usecase.PrefetchExchangeIconsUseCase
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {

    single<NetworkFlipperPlugin> {
        check(BuildConfig.DEBUG) { "NetworkFlipperPlugin só deve ser injetado no modo DEBUG" }
        NetworkFlipperPlugin()
    }

    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(LoggingInterceptor())
                addNetworkInterceptor(FlipperOkhttpInterceptor(get()))
            }
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://rest.coinapi.io/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .build()
            .create(ExchangeApiService::class.java)
    }

    single { GetExchangeIconUrlByIdUseCase(get()) }
    single { PrefetchExchangeIconsUseCase(get()) }
}