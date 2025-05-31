package com.velosobr.core.error

sealed class AppException(message: String) : Exception(message) {
    object Network : AppException("Connection Error: Check your internet connection")
    data class Api(val code: Int, override val message: String?) :
        AppException("API Error($code): $message")

    data class Unknown(val error: Throwable) : AppException(error.message ?: "Unknown Error")
}