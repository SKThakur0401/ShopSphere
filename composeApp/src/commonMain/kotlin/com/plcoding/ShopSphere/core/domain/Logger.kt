package com.plcoding.ShopSphere.core.domain

import io.github.aakira.napier.Napier

expect class Logger() {
    fun setupLogging()
    fun d(message: String, throwable: Throwable? = null)
    fun i(message: String, throwable: Throwable? = null)
    fun w(message: String, throwable: Throwable? = null)
    fun e(message: String, throwable: Throwable? = null)
}
