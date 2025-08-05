package com.plcoding.ShopSphere.login_signup.presentation.login


sealed interface AuthActions{

    data class OnNameChange(val newName : String) : AuthActions
    data class OnEmailChange(val newEmail : String) : AuthActions
    data class OnPasswordChange(val newPassword : String) : AuthActions

    data object Submit : AuthActions
}

