package com.plcoding.ShopSphere.di

import org.koin.core.module.Module
import org.koin.dsl.module
import com.plcoding.ShopSphere.login_signup.presentation.login.AuthViewModel
import org.koin.core.module.dsl.viewModelOf

expect val platformModule: Module

val sharedModule = module {

    viewModelOf(::AuthViewModel)
}
