package com.velosobr.exchange_list.presentation

import com.velosobr.domain.model.Exchange

data class ExchangeListState(
    val isLoading: Boolean = true,
    val exchanges: List<Exchange> = emptyList(),
    val exchangeIcons: Map<String, String?> = emptyMap(),
    val isRefreshing: Boolean = false,
    val error: String? = null
)