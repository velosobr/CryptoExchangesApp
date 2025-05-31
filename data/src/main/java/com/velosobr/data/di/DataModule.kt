package com.velosobr.data.di

import com.velosobr.data.remote.api.CoinApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://rest.coinapi.io/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(CoinApiService::class.java)
    }
}