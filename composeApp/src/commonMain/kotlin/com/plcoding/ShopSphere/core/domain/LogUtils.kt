package com.plcoding.ShopSphere.core.domain

object LogUtils {
    private val logger = Logger()
    
    fun setupLogging() {
        logger.setupLogging()
    }
    
    fun d(message: String, throwable: Throwable? = null) {
        logger.d(message, throwable)
    }
    
    fun i(message: String, throwable: Throwable? = null) {
        logger.i(message, throwable)
    }
    
    fun w(message: String, throwable: Throwable? = null) {
        logger.w(message, throwable)
    }
    
    fun e(message: String, throwable: Throwable? = null) {
        logger.e(message, throwable)
    }
}
