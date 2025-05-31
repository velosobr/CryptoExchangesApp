package com.velosobr.cryptoexchangesapp.di

import com.velosobr.cryptoexchangesapp.BuildConfig
import com.velosobr.data.repository.ExchangeRepositoryImpl
import com.velosobr.domain.repository.ExchangeRepository
import org.koin.dsl.module

val appModule = module {
    single { HelloService() }

    single<ExchangeRepository> {
        ExchangeRepositoryImpl(
            api = get(),
            apiKey = BuildConfig.API_KEY
        )
    }
}

class HelloService {
    fun getMessage(): String = "Hello from Koin and apptheme!"
}