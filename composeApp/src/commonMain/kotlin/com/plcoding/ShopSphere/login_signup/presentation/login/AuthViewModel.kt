package com.plcoding.ShopSphere.login_signup.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.ShopSphere.app.TokenManager
import com.plcoding.ShopSphere.core.domain.LogUtils
import com.plcoding.ShopSphere.login_signup.data.dataModels.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AuthViewModel(private val tokenManager: TokenManager, private val supabase: SupabaseClient) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun trial(){
        val s = tokenManager.getStr()
        s?.let {st->
            _state.update {
                it.copy(name = st)
            }
        }
    }

    fun onAction(action : AuthActions){
        when(action){

            is AuthActions.OnNameChange->{
                tokenManager.saveStr(action.newName)

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

            is AuthActions.OnConfirmPasswordChange -> {
                _state.update {
                    it.copy(confirmPassword = action.newPassword)
                }
            }

            AuthActions.Login -> {
                if(isValid()) login()
            }

            AuthActions.Register -> {
                if(isValid()) {
                    if(state.value.password == state.value.confirmPassword) register()
                    else showError("Password not matching!")
                }
            }
        }
    }


    fun checkSignInStatus() {
        LogUtils.i("AuthViewModel: Checking sign in status")
        viewModelScope.launch {
            try {
                val session = supabase.auth.currentSessionOrNull()
                LogUtils.i("AuthViewModel: Session check result - session: ${session != null}")
                if (session != null) {
                    _state.update { it.copy(isSignedIn = true) }
                    LogUtils.i("AuthViewModel: User is signed in")
                } else {
                    _state.update { it.copy(isSignedIn = false) }
                    LogUtils.i("AuthViewModel: User is not signed in")
                }
            } catch (ex : Exception){
                showError("Signin Status check fail : ${ex.message ?: ""}")
                _state.update { it.copy(isSignedIn = false) }
            }
        }
    }

    private fun login() {
        LogUtils.i("AuthViewModel: Starting login process for email: ${state.value.email}")
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            try {
                supabase.auth.signInWith(Email){
                    email = state.value.email
                    password = state.value.password
                }
                LogUtils.i("AuthViewModel: Login successful")
                saveToken()
            } catch (ex: Exception){
                handleAuthError(ex)
            }
        }
    }

    private fun register() = viewModelScope.launch {
        LogUtils.i("AuthViewModel: Starting registration process for email: ${state.value.email}")
        _state.update { it.copy(isLoading = true, error = null) }

        try {
            supabase.auth.signUpWith(Email) {
                email = state.value.email
                password = state.value.password
            }

            val userId = supabase.auth.currentUserOrNull()?.id   // get ID of just created user
            if (userId == null) {
                showError("AuthViewModel: User ID is null after registration")
                return@launch
            }

            // Build row using proper data class
            val userRow = User(
                userId = userId,
                userName = state.value.name,
                password = state.value.password,
                email = state.value.email,
                cartItemList = emptyList()
            )

            LogUtils.d("AuthViewModel: Inserting user row: $userRow")
            // Insert using the data class
            supabase.from("users").insert(userRow)

            LogUtils.i("AuthViewModel: Registration successful")
            // Save token (will be non-null only if email confirmation is OFF)
            saveToken()
        } catch (e: Exception) {
            LogUtils.e("AuthViewModel: Registration failed", e)
            showError(e.message ?: "Registration failed")
        }
    }

    fun logoutUser(){
        viewModelScope.launch {
            try {
                supabase.auth.signOut()
                tokenManager.saveAuthToken("")

                _state.update { it.copy(isSignedIn = false) }   // On basis of this state change
            }catch (ex : Exception){                            // HomeScreen will logout
                showError(ex.message ?: "Auth VM logout failed")
            }
        }
    }



    private fun isValid(): Boolean{
        val email = state.value.email
        val password = state.value.password
        when{
            email.contains("@").not() -> _state.update { it.copy(error = "No @ in your email!") }
            email.contains(".com").not() -> _state.update { it.copy(error = "No .com in your email!") }
            password.length < 6 -> _state.update { it.copy(error = "Password should have at least 6 chars") }
            else -> return true
        }
        return false
    }

    // Helper function for better error handling
    private fun handleAuthError(exception: Exception) {
        LogUtils.w("AuthViewModel: Handling auth error: ${exception.message}")
        val error = when {
            exception.message?.contains("Invalid login credentials", ignoreCase = true) == true ->
                "Invalid email or password"
            exception.message?.contains("Email not confirmed", ignoreCase = true) == true ->
                "Please verify your email address before logging in"
            exception.message?.contains("Too many requests", ignoreCase = true) == true ->
                "Too many login attempts. Please wait before trying again"
            exception.message?.contains("User not found", ignoreCase = true) == true ->
                "No account found with this email"
            exception.message?.contains("Signup is disabled", ignoreCase = true) == true ->
                "Account registration is currently disabled"
            exception.message?.contains("network", ignoreCase = true) == true ||
                    exception.message?.contains("timeout", ignoreCase = true) == true ->
                "Network error. Please check your internet connection"
            else -> exception.message ?: "An unexpected error occurred. Please try again"
        }
        showError(error)
    }

    private fun saveToken(){
        val accessToken = supabase.auth.currentAccessTokenOrNull()
        accessToken?.let {token->
            tokenManager.saveAuthToken(token)
            _state.update {
                it.copy(isLoading = false, success = true, error = null, isSignedIn = true)
            }
        }?: run {
            showError("Access token Not generated!")
        }
    }

    private fun showError(error : String){
        LogUtils.e("AuthViewModel: Error occurred: $error")
        _state.update {
            it.copy(isLoading = false, error = error)
        }
    }
}

class FakeAuthRepo {
    suspend fun login(email: String, password: String): Boolean {
        delay(1000) // simulate network
        return email == "skt" && password == "skt"
    }
}

