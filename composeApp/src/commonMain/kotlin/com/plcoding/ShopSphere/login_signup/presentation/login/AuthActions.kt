package com.plcoding.ShopSphere.login_signup.presentation.login


sealed interface AuthActions{

    data class OnNameChange(val newName : String) : AuthActions
    data class OnEmailChange(val newEmail : String) : AuthActions
    data class OnPasswordChange(val newPassword : String) : AuthActions
    data class OnConfirmPasswordChange(val newPassword : String) : AuthActions

    data object Login : AuthActions
    data object Register : AuthActions

}

