package com.velosobr.cryptoexchangesapp.di

import org.koin.dsl.module

val appModule = module {
    single { HelloService() }
}

class HelloService {
    fun getMessage(): String = "Hello from Koin and apptheme!"
}