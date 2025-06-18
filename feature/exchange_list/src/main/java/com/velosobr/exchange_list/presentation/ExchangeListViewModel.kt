package com.velosobr.exchange_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.core.result.ExchangeResult
import com.velosobr.domain.usecase.GetExchangeIconUrlByIdUseCase
import com.velosobr.domain.usecase.PrefetchExchangeIconsUseCase
import com.velosobr.domain.usecase.GetExchangesUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExchangeListViewModel(
    private val getExchangesUseCase: GetExchangesUseCase,
    private val getExchangeIconsUseCase: GetExchangeIconUrlByIdUseCase,
    private val prefetchExchangeIconsUseCase: PrefetchExchangeIconsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ExchangeListState())
    val state: StateFlow<ExchangeListState> = _state.asStateFlow()

    init {
        fetchExchanges()
    }

    fun fetchExchanges() {
        _state.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            when (val result = getExchangesUseCase()) {
                is ExchangeResult.Success -> {
                    val exchanges = result.data
                    prefetchExchangeIconsUseCase()
                    val iconsMap = coroutineScope {
                        exchanges.map { exchange ->
                            async {
                                exchange.exchangeId to getExchangeIconsUseCase(exchange.exchangeId)
                            }
                        }.awaitAll().toMap()
                    }

                    _state.update {
                        it.copy(
                            isLoading = false,
                            exchanges = exchanges,
                            exchangeIcons = iconsMap,
                            error = null
                        )
                    }
                }

                is ExchangeResult.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.exception.message ?: "Erro desconhecido"
                        )
                    }
                }
            }
        }
    }
}