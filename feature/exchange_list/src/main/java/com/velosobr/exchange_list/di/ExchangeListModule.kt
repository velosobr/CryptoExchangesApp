package com.velosobr.exchange_list.di

import com.velosobr.domain.usecase.GetExchangesUseCase
import com.velosobr.exchange_list.presentation.ExchangeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exchangeListModule = module {
    viewModel { ExchangeListViewModel(get()) }

    factory {
        GetExchangesUseCase(
            repository = get()
        )
    }
}
