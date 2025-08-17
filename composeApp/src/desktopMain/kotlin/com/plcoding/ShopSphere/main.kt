package com.plcoding.ShopSphere

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.plcoding.ShopSphere.app.App
import com.plcoding.ShopSphere.core.domain.LogUtils
import com.plcoding.ShopSphere.di.initKoin

fun main() {
    // Initialize logging
    LogUtils.setupLogging()
    LogUtils.i("Desktop: Application started")
    
    initKoin()
    application {
            Window(
                onCloseRequest = ::exitApplication,
                title = "Desky Shopsphere!!",
            ) {
                App()
            }
        }
}