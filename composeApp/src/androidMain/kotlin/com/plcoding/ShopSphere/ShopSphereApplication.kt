package com.plcoding.ShopSphere

import android.app.Application
import com.plcoding.ShopSphere.di.initKoin
import org.koin.android.ext.koin.androidContext


class ShopSphereApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ShopSphereApplication)
        }
    }
}