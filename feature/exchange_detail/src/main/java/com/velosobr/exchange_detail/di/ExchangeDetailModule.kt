package com.velosobr.exchange_detail.di

import com.velosobr.domain.usecase.GetExchangeByIdUseCase
import com.velosobr.exchange_detail.presentation.ExchangeDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exchangeDetailModule = module {

    viewModel { (exchangeId: String) ->
        ExchangeDetailViewModel(
            exchangeId = exchangeId,
            getExchangeByIdUseCase = get()
        )
    }
    factory {
        GetExchangeByIdUseCase(
            repository = get()
        )
    }
}