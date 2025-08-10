package com.plcoding.ShopSphere.app

import kotlinx.serialization.Serializable

sealed interface NavGraphA {
    @Serializable
    data object Root : NavGraphA

    @Serializable
    data object LoginScreen : NavGraphA

    @Serializable
    data object RegistrationScreen : NavGraphA

    @Serializable
    data object SplashScreen : NavGraphA

    @Serializable
    data object practice : NavGraphA

    @Serializable
    data object HomeScreen : NavGraphA
}

