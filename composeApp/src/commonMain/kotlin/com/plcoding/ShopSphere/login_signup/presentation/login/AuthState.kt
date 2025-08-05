package com.plcoding.ShopSphere.login_signup.presentation.login

data class AuthState(
    val name : String = "",
    val email :String = "",
    val password: String= "",
    val confirmPassword: String= "",
    val isLoading:Boolean = false,
    val error : String? = null,
    val success :Boolean= false
)

