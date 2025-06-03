package com.velosobr.exchange_list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.core.result.ExchangeResult
import com.velosobr.core.state.UiState
import com.velosobr.domain.model.Exchange
import com.velosobr.domain.usecase.GetExchangeIconUrlUseCase
import com.velosobr.domain.usecase.GetExchangesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExchangeListViewModel(
    private val getExchangesUseCase: GetExchangesUseCase,
    private val getExchangeIconsUseCase: GetExchangeIconUrlUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Exchange>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Exchange>>> = _uiState.asStateFlow()
    private val _exchangeIcons = MutableStateFlow<Map<String, String?>>(emptyMap())
    val exchangeIcons: StateFlow<Map<String, String?>> = _exchangeIcons

    init {
        fetchExchanges()
    }

    fun fetchExchanges() {
        viewModelScope.launch {
            Log.d("ExchangeListViewModel", "Iniciando fetchExchanges")
            _uiState.value = UiState.Loading
            Log.d("ExchangeListViewModel", "Estado da UI definido como Loading")

            val exchanges = getExchangesUseCase()
            Log.d("ExchangeListViewModel", "Resultado do getExchangesUseCase obtido: $exchanges")

            _uiState.value = when (exchanges) {
                is ExchangeResult.Success -> {
                    Log.d(
                        "ExchangeListViewModel",
                        "ExchangeResult.Success recebido com ${exchanges.data.size} exchanges"
                    )

                    exchanges.data.forEach { exchange ->
                        launch {
                            val icon = getExchangeIconsUseCase(exchange.exchangeId)
                            _exchangeIcons.update { it + (exchange.exchangeId to icon) }
                        }
                    }
                    Log.d("ExchangeListViewModel", "Exchanges com ícones processadas com sucesso")
                    UiState.Success(exchanges.data)
                }

                is ExchangeResult.Error -> {
                    Log.e(
                        "ExchangeListViewModel",
                        "Erro ao obter exchanges: ${exchanges.exception.message}"
                    )
                    UiState.Error(message = exchanges.exception.message ?: "Erro desconhecido")
                }
            }
            Log.d("ExchangeListViewModel", "Estado da UI atualizado: ${_uiState.value}")
        }
    }
}