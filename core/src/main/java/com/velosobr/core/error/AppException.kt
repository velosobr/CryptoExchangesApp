package com.velosobr.core.error

sealed class AppException(message: String?) : Exception(message) {
    data object Network : AppException("Connection error")
    data object Unauthorized : AppException("Unauthorized access")
    data object NotFound : AppException("Resource not found")
    data class Unknown(val error: Throwable) : AppException(error.message ?: "Unknown error")
}