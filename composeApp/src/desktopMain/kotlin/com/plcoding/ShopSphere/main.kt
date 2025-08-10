package com.plcoding.ShopSphere

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.plcoding.ShopSphere.app.App
import com.plcoding.ShopSphere.di.initKoin

fun main() {
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