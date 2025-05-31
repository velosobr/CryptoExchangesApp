package com.velosobr.core.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CoroutineScope.safeLaunch(block: suspend () -> Unit) {
    launch(Dispatchers.IO) {
        try {
            block()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}