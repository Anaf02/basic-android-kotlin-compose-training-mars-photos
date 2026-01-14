package com.example.marsphotos.ui

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun runCoroutine(
    coroutineScope: CoroutineScope,
    block: suspend () -> Unit,
    errorHandler: (e: Throwable) -> Unit,
) {
    coroutineScope.launch {
        try {
            block()
        } catch (_: CancellationException) {
        } catch (throwable: Throwable) {
            errorHandler(throwable)
        }
    }
}