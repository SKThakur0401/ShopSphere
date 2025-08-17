package com.plcoding.ShopSphere.di

import com.russhwolf.settings.Settings
import com.russhwolf.settings.PreferencesSettings
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.prefs.Preferences

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        single { provideSettings() }
    }

private fun provideSettings(): Settings {
    // java.util.prefs.Preferences works on Windows, macOS, Linux JVM
    val prefs = Preferences.userRoot().node("MyAppPreferences")
    return PreferencesSettings(prefs)
}

