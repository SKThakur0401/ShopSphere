package com.plcoding.ShopSphere.login_signup.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    private val authRepo = FakeAuthRepo()

    fun onAction(action : AuthActions){
        when(action){

            is AuthActions.OnNameChange->{
                _state.update {
                    it.copy(name = action.newName)
                }
            }

            is AuthActions.OnEmailChange->{
                _state.update {
                    it.copy(email = action.newEmail)
                }
            }

            is AuthActions.OnPasswordChange -> {
                _state.update {
                    it.copy(password = action.newPassword)
                }
            }

            AuthActions.Submit -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            val result = authRepo.login(_state.value.email, _state.value.password)

            _state.value = _state.value.copy(
                isLoading = false,
                success = result,
                error = if (!result) "Login failed" else null
            )
        }
    }

}

class FakeAuthRepo {
    suspend fun login(email: String, password: String): Boolean {
        delay(1000) // simulate network
        return email == "skt" && password == "skt"
    }
}

