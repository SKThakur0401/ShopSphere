package com.plcoding.ShopSphere.login_signup.presentation.login

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
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
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
import com.plcoding.ShopSphere.core.presentation.GlobalToast
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.material.Icon as Icon


@Composable
fun LoginScreenRoot(
    viewModel: AuthViewModel = koinViewModel(),
    navigateToRegister: () -> Unit,
    gotoNotesScreen: () -> Unit,
    onLoginSuccess: () -> Unit
){
    val state by viewModel.state.collectAsState()

    LoginScreen(state, navigateToRegister, onLoginSuccess, viewModel)
    Button(
        onClick = gotoNotesScreen
    ){
        Text("Goto Notes screen")
    }
}


@Composable
fun LoginScreen(
    state: AuthState,
    navigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {
    state.error?.let {
        GlobalToast.state.show(it)

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBackground)
    ) {
        // Decorative top pattern
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            drawRect(
                brush = Brush.linearGradient(
                    colors = listOf(primaryColor, secondaryColor),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, size.height)
                ),
                size = size
            )

            // Draw pattern overlay
            val patternSize = 40.dp.toPx()
            for (x in 0..(size.width / patternSize).toInt()) {
                drawCircle(
                    color = accentColor.copy(alpha = 0.2f),
                    center = Offset(x * patternSize, size.height / 2),
                    radius = patternSize / 3
                )
            }
        }

        // Main card with elevation and animation
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.Center)
                .heightIn(min = 400.dp)
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
                    text = "Welcome Back",
                    style = MaterialTheme.typography.h2,
                    color = darkText,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Sign in to your account",
                    style = MaterialTheme.typography.body1,
                    color = darkText.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Email field
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { viewModel.onAction(AuthActions.OnEmailChange(it)) },
//                    label = { Text("Email Address")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = MaterialTheme.shapes.medium,
                    placeholder = { Text("Email", color = darkText.copy(alpha = 0.7f)) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = darkText,
                        focusedBorderColor = primaryColor,
                        unfocusedBorderColor = secondaryColor.copy(alpha = 0.5f),
                        cursorColor = accentColor
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email,
                            tint = accentColor.copy(alpha = 0.9f),
                            contentDescription = "Email")
                    }
                )

                // Password field
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { viewModel.onAction(AuthActions.OnPasswordChange(it)) },
                    placeholder = { Text("Password", color = darkText.copy(alpha = 0.7f)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = darkText,
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
                        Icon(imageVector = Icons.Default.Lock,
                            tint = accentColor.copy(alpha = 0.9f),
                            contentDescription = "Password")
                    }
                )

                // Login button
                Button(
                    onClick = { viewModel.onAction(AuthActions.Submit) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = primaryColor,
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.large,
                    enabled = state.isLoading.not()
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            color = primaryColor,
                            strokeWidth = 3.dp,
                            modifier = Modifier.size(29.dp)
                        )
                    } else {
                        Text("Sign In", style = MaterialTheme.typography.button)
                    }
                }

                // Divider with text
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier.weight(1f),
                        color = secondaryColor.copy(alpha = 0.3f),
                        thickness = 1.dp
                    )

                    Text(
                        text = "OR",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = darkText.copy(alpha = 0.5f)
                    )

                    Divider(
                        modifier = Modifier.weight(1f),
                        color = secondaryColor.copy(alpha = 0.3f),
                        thickness = 1.dp
                    )
                }

                // Register button
                TextButton(
                    onClick = navigateToRegister,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Create an Account",
                        color = accentColor,
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }

        // Decorative bottom element
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            secondaryColor.copy(alpha = 0.1f)
                        )
                    )
                )
        )
    }
}



//@Composable
//fun LoginScreen() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        var email by remember { mutableStateOf("") }
//        var password by remember { mutableStateOf("") }
//
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email") },
//            modifier = Modifier.fillMaxWidth(),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                textColor = darkText,
//                cursorColor = accentColor,
//                focusedBorderColor = primaryColor,
//                unfocusedBorderColor = secondaryColor,
//                focusedLabelColor = primaryColor,
//                unfocusedLabelColor = secondaryColor,
//                placeholderColor = secondaryColor
//            )
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password") },
//            visualTransformation = PasswordVisualTransformation(),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Button(
//            onClick = { /* Handle login */ },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Login")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        TextButton(
//            onClick = { /* Navigate to registration */ }
//        ) {
//            Text("Don't have an account? Register")
//        }
//    }
//}