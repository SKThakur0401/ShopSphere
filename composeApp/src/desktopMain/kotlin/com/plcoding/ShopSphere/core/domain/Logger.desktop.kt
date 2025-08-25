package com.plcoding.ShopSphere.core.domain

import io.github.aakira.napier.Antilog
import io.github.aakira.napier.Napier
import io.github.aakira.napier.DebugAntilog
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

actual class Logger {
    private val logFile = File(System.getProperty("user.home"), "ShopSphere_desktop.log")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
    
    actual fun setupLogging() {
        // Use DebugAntilog for console output and also write to file
        Napier.base(DebugAntilog())
        
        // Log the setup
        Napier.i("Logging initialized. Log file: ${logFile.absolutePath}")
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
