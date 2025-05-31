package com.velosobr.core.result

import com.velosobr.core.error.AppException

sealed class ExchangeResult<out T> {
    data class Success<T>(val data: T) : ExchangeResult<T>()
    data class Error(val exception: AppException) : ExchangeResult<Nothing>()

    fun isSuccess(): Boolean = this is Success
    fun isError(): Boolean = this is Error
}