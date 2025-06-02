package com.velosobr.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.nanoTime()

        val response = chain.proceed(request)

        val endTime = System.nanoTime()
        val durationMs = (endTime - startTime) / 1e6

        Timber.tag("Network")
            .d("→ ${request.method()} ${request.url()} | ← ${response.code()} (${durationMs}ms)")

        return response
    }
}