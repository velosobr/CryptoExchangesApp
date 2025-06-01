package com.velosobr.cryptoexchangesapp

import android.app.Application
import com.velosobr.cryptoexchangesapp.di.appModule
import com.velosobr.data.di.dataModule
import com.velosobr.exchange_detail.di.exchangeDetailModule
import com.velosobr.exchange_list.di.exchangeListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    exchangeListModule,
                    exchangeDetailModule,
                    dataModule,
                )
            )
        }
    }
}