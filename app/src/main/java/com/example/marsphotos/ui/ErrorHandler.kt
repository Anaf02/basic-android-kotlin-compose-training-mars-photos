package com.example.marsphotos.ui

import android.util.Log

interface ErrorHandler {
    fun onError(error: Throwable)

    fun log(error: Throwable)
}

class MarsErrorHandler() : ErrorHandler {
    override fun onError(error: Throwable) {
        log(error)
    }

    override fun log(error: Throwable) {
        Log.e("ERROR", error.message.toString(), error)
    }
}