package com.plcoding.ShopSphere.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.ShopSphere.login_signup.presentation.login.AuthViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreen(onLogoutNavigation: () -> Unit, authViewModel: AuthViewModel) {

    val state by authViewModel.state.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to Home Screen!!!",
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp)) // space between text and button

            Button(onClick = { authViewModel.logoutUser() }) {
                Text(text = "Logout")
            }
        }
        var handledLogoutNav = false
        LaunchedEffect(state.isSignedIn){
            if(state.isSignedIn == false){
                handledLogoutNav = true
                onLogoutNavigation()
            }
        }
    }
}

