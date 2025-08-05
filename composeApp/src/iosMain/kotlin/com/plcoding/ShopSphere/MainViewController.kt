package com.plcoding.ShopSphere

import androidx.compose.ui.window.ComposeUIViewController
import com.plcoding.ShopSphere.app.App
import com.plcoding.ShopSphere.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }