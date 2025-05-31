package com.velosobr.exchange_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.core.result.ExchangeResult
import com.velosobr.core.state.UiState
import com.velosobr.domain.model.Exchange
import com.velosobr.domain.usecase.GetExchangesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeListViewModel(
    private val getExchangesUseCase: GetExchangesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Exchange>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Exchange>>> = _state.asStateFlow()

    init {
        fetchExchanges()
    }

    private fun fetchExchanges() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            val result = getExchangesUseCase()
            _state.value = when (result) {
                is ExchangeResult.Success -> UiState.Success(result.data)
                is ExchangeResult.Error -> UiState.Error(result.exception.message ?: "Erro desconhecido")
            }
        }
    }
}