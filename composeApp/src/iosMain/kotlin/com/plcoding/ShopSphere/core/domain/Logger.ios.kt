package com.plcoding.ShopSphere.core.domain

import io.github.aakira.napier.Antilog
import io.github.aakira.napier.Napier
import io.github.aakira.napier.DebugAntilog

actual class Logger {
    actual fun setupLogging() {
        Napier.base(DebugAntilog())
    }
    
    actual fun d(message: String, throwable: Throwable?) {
        if (throwable != null) {
            Napier.d(message = message, throwable = throwable)
        } else {
            Napier.d(message = message)
        }
    }
    
    actual fun i(message: String, throwable: Throwable?) {
        if (throwable != null) {
            Napier.i(message = message, throwable = throwable)
        } else {
            Napier.i(message = message)
        }
    }
    
    actual fun w(message: String, throwable: Throwable?) {
        if (throwable != null) {
            Napier.w(message = message, throwable = throwable)
        } else {
            Napier.w(message = message)
        }
    }
    
    actual fun e(message: String, throwable: Throwable?) {
        if (throwable != null) {
            Napier.e(message = message, throwable = throwable)
        } else {
            Napier.e(message = message)
        }
    }
}
