package com.plcoding.ShopSphere.login_signup.presentation.login

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.plcoding.ShopSphere.app.accentColor
import com.plcoding.ShopSphere.app.darkText
import com.plcoding.ShopSphere.app.lightBackground
import com.plcoding.ShopSphere.app.primaryColor
import com.plcoding.ShopSphere.app.secondaryColor
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.material.Icon as Icon

@Composable
fun RegistrationScreenRoot(
    viewModel: AuthViewModel = koinViewModel(),
    navigateToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
){
    val state by viewModel.state.collectAsState()
    RegistrationScreen(state, navigateToLogin, onRegisterSuccess, viewModel)
}


@Composable
fun RegistrationScreen(
    state: AuthState,
    navigateToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBackground)
    ) {
        // Animated background pattern
        val infiniteTransition = rememberInfiniteTransition()
        val translateX by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 100f,
            animationSpec = infiniteRepeatable(
                animation = tween(20000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val tileSize = 80.dp.toPx()

            for (x in -1..(width / tileSize).toInt() + 1) {
                for (y in 0..(height / tileSize).toInt()) {
                    val offsetX = (x * tileSize) + (translateX % tileSize)
                    val offsetY = y * tileSize

                    val color = when ((x + y) % 3) {
                        0 -> primaryColor.copy(alpha = 0.05f)
                        1 -> secondaryColor.copy(alpha = 0.05f)
                        else -> accentColor.copy(alpha = 0.05f)
                    }

                    drawRect(
                        color = color,
                        topLeft = Offset(offsetX, offsetY),
                        size = Size(tileSize, tileSize)
                    )
                }
            }
        }

        // Main registration card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.Center)
                .heightIn(min = 500.dp)
                .shadow(16.dp, shape = RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.h2,
                    color = darkText,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Name field
                OutlinedTextField(
                    value = state.name,
                    onValueChange = { viewModel.onAction(AuthActions.OnNameChange(it)) },
                    label = {"Full Name"},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = primaryColor,
                        unfocusedBorderColor = secondaryColor.copy(alpha = 0.5f),
                        cursorColor = accentColor
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = "Name")
                    }
                )

                // Email field
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { viewModel.onAction(AuthActions.OnNameChange(it)) },
                    label = { Text("Email Address") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = primaryColor,
                        unfocusedBorderColor = secondaryColor.copy(alpha = 0.5f),
                        cursorColor = accentColor
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = "Email")
                    }
                )

                // Password field
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { viewModel.onAction(AuthActions.OnPasswordChange(it))  },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = primaryColor,
                        unfocusedBorderColor = secondaryColor.copy(alpha = 0.5f),
                        cursorColor = accentColor
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = "Password")
                    }
                )

                // Confirm Password field
                OutlinedTextField(
                    value = state.confirmPassword,
                    onValueChange = { viewModel.onAction(AuthActions.OnPasswordChange(it)) },
                    label = { /*Text(*/"Confirm Password" },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = primaryColor,
                        unfocusedBorderColor = secondaryColor.copy(alpha = 0.5f),
                        cursorColor = accentColor
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = "Confirm Password")
                    }
                )

                // Register button
                Button(
                    onClick = { viewModel.onAction(AuthActions.Submit)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = accentColor,
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.large,
                    enabled = state.isLoading.not()
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text("Create Account", style = MaterialTheme.typography.button)
                    }
                }

                // Login option
                TextButton(
                    onClick = navigateToLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Text(
                        text = "Already have an account? Sign In",
                        color = primaryColor,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }

        // Decorative top element
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            primaryColor.copy(alpha = 0.2f),
                            Color.Transparent
                        )
                    )
                )
        )
    }
}