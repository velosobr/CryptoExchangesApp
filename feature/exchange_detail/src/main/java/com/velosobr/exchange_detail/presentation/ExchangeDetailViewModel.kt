package com.velosobr.exchange_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.core.result.ExchangeResult
import com.velosobr.core.state.UiState
import com.velosobr.domain.usecase.GetExchangeByIdUseCase
import com.velosobr.exchange_detail.model.ExchangeDetailModel
import com.velosobr.exchange_detail.model.toDetailModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExchangeDetailViewModel(
    private val exchangeId: String,
    private val getExchangeByIdUseCase: GetExchangeByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<ExchangeDetailModel>>(UiState.Loading)
    val uiState: StateFlow<UiState<ExchangeDetailModel>> = _uiState

    init {
        fetchExchangeDetail()
    }

    fun fetchExchangeDetail() {

        viewModelScope.launch {
            when (val result = getExchangeByIdUseCase(exchangeId)) {
                is ExchangeResult.Success -> {
                    result.data?.let {
                        _uiState.value = UiState.Success(it.toDetailModel())
                    } ?: run {
                        _uiState.value = UiState.Error("Exchange not found")
                    }
                }

                is ExchangeResult.Error -> {
                    _uiState.value = UiState.Error(result.exception.message ?: "Unknown error")
                }
            }
        }
    }
}