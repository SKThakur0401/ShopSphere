package com.plcoding.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.plcoding.ShopSphere.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Desky Shopsphere!!",
    ) {
        App()
    }
}