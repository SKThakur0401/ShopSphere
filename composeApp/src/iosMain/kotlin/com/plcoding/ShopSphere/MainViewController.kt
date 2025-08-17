package com.plcoding.ShopSphere

import androidx.compose.ui.window.ComposeUIViewController
import com.plcoding.ShopSphere.app.App
import com.plcoding.ShopSphere.core.domain.LogUtils
import com.plcoding.ShopSphere.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        // Initialize logging
        LogUtils.setupLogging()
        LogUtils.i("iOS: Application started")
        initKoin()
    }
) { App() }