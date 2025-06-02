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

    private val _uiState = MutableStateFlow<UiState<List<Exchange>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Exchange>>> = _uiState.asStateFlow()

    init {
        fetchExchanges()
    }

    fun fetchExchanges() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = getExchangesUseCase()
            _uiState.value = when (result) {
                is ExchangeResult.Success -> UiState.Success(result.data)
                is ExchangeResult.Error -> UiState.Error(message = result.exception.message ?: "Erro desconhecido")
            }
        }
    }
}