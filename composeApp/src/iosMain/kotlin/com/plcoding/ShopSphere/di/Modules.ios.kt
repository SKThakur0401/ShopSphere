package com.plcoding.ShopSphere.di

import com.russhwolf.settings.Settings
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module
import com.russhwolf.settings.NSUserDefaultsSettings
import platform.Foundation.NSUserDefaults

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Darwin.create() }
        single { provideSettings() }
    }

private fun provideSettings(): Settings {
    val defaults = NSUserDefaults.standardUserDefaults
    return NSUserDefaultsSettings(defaults)
}