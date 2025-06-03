package com.velosobr.cryptoexchangesapp.di

import com.velosobr.cryptoexchangesapp.BuildConfig
import com.velosobr.data.local.ExchangeIconDataStore
import com.velosobr.data.repository.ExchangeIconRepositoryImpl
import com.velosobr.data.repository.ExchangeRepositoryImpl
import com.velosobr.domain.repository.ExchangeIconRepository
import com.velosobr.domain.repository.ExchangeRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { ExchangeIconDataStore(androidContext()) }

    single<ExchangeRepository> {
        ExchangeRepositoryImpl(
            api = get(),
            apiKey = BuildConfig.API_KEY
        )
    }

    single<ExchangeIconRepository> {
        ExchangeIconRepositoryImpl(
            apiKey = BuildConfig.API_KEY,
            api = get(),
            dataStore = get()
        )
    }
}