package com.plcoding.ShopSphere.login_signup.presentation.login

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.ShopSphere.core.presentation.GlobalToast
import com.plcoding.ShopSphere.core.presentation.SimpleShopSphereLogo
import kotlin.math.*


@Composable
fun LoginScreenRoot(
    viewModel: AuthViewModel,
    navigateToRegister: () -> Unit,
    gotoNotesScreen: () -> Unit,
    onLoginSuccess: () -> Unit
){
    val state by viewModel.state.collectAsState()
    LoginScreen(state, navigateToRegister, onLoginSuccess, viewModel)
}


@Composable
fun LoginScreen(
    state: AuthState,
    navigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: AuthViewModel
) {
    state.error?.let {
        LaunchedEffect(it){ // This prevents toast re-appearance coz of recompositn
            GlobalToast.state.show(it)      // Now this toast will be visible each time u press login
        }                               // and login fails... HOW??? Because each time u press
        // login, state.error becomes "NULL" bcoz of Loading state and then it repopulates the
        // value with "Login Failed" hence there's a chnage in value and it appears eacah time
        // u press login with wrong credential... despite each time the text is same ;)
    }

    LaunchedEffect(state.isSignedIn){
        if(state.isSignedIn == true) onLoginSuccess()
    }

    var passwordVisible by remember { mutableStateOf(false) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2D1810),
                        Color(0xFF1A0F0A)
                    )
                )
            )
    ) {
        // Animated geometric pattern background
        val infiniteTransition = rememberInfiniteTransition()
        val animatedOffset by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 100f,
            animationSpec = infiniteRepeatable(
                animation = tween(20000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
        
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.1f)
        ) {
            val patternSize = 60.dp.toPx()
            val diagonalOffset = animatedOffset
            
            for (x in -2..(size.width / patternSize).toInt() + 2) {
                for (y in -2..(size.height / patternSize).toInt() + 2) {
                    val centerX = x * patternSize + diagonalOffset
                    val centerY = y * patternSize + diagonalOffset / 2
                    
                    // Draw hexagon pattern
                    val path = Path().apply {
                        moveTo(centerX + patternSize / 2 * cos(0f), centerY + patternSize / 2 * sin(0f))
                        for (i in 1..6) {
                            val angle = i * 60f * PI.toFloat() / 180f
                            lineTo(
                                centerX + patternSize / 2 * cos(angle),
                                centerY + patternSize / 2 * sin(angle)
                            )
                        }
                        close()
                    }
                    
                    drawPath(
                        path = path,
                        color = Color(0xFFB8860B),
                        style = Stroke(width = 1.dp.toPx())
                    )
                }
            }
        }

        // Floating glass morphism card
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(32.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.1f),
                            Color.White.copy(alpha = 0.05f)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo section
                SimpleShopSphereLogo(
                    logoSize = 80.dp,
                    color = Color(0xFFB8860B)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.h2.copy(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Sign in to continue your journey",
                    style = MaterialTheme.typography.body1,
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 32.dp),
                    textAlign = TextAlign.Center
                )

                // Email field with modern styling
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { viewModel.onAction(AuthActions.OnEmailChange(it)) },
                    label = { Text("Email", color = Color.White.copy(alpha = 0.7f)) },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Email,
                            contentDescription = "Email",
                            tint = Color(0xFFB8860B)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        cursorColor = Color(0xFFB8860B),
                        focusedBorderColor = Color(0xFFB8860B),
                        unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                        focusedLabelColor = Color(0xFFB8860B),
                        unfocusedLabelColor = Color.White.copy(alpha = 0.5f)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )

                // Password field with visibility toggle
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { viewModel.onAction(AuthActions.OnPasswordChange(it)) },
                    label = { Text("Password", color = Color.White.copy(alpha = 0.7f)) },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Lock,
                            contentDescription = "Password",
                            tint = Color(0xFFB8860B)
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { passwordVisible = !passwordVisible }
                        ) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Star else Icons.Default.Lock,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                tint = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        cursorColor = Color(0xFFB8860B),
                        focusedBorderColor = Color(0xFFB8860B),
                        unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                        focusedLabelColor = Color(0xFFB8860B),
                        unfocusedLabelColor = Color.White.copy(alpha = 0.5f)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true
                )
                
                // Forgot password link
                TextButton(
                    onClick = { /* Handle forgot password */ },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 8.dp, bottom = 24.dp)
                ) {
                    Text(
                        text = "Forgot Password?",
                        color = Color(0xFFD4A574),
                        style = MaterialTheme.typography.body2
                    )
                }

                // Animated login button
                val buttonScale by animateFloatAsState(
                    targetValue = if (state.isLoading) 0.95f else 1f,
                    animationSpec = tween(200)
                )
                
                Button(
                    onClick = { viewModel.onAction(AuthActions.Login) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .scale(buttonScale),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFB8860B)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    enabled = state.isLoading.not()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(24.dp)
                            )
                        } else {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "Sign In",
                                    style = MaterialTheme.typography.button.copy(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }

                // Divider with ornament
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.White.copy(alpha = 0.3f)
                                    )
                                )
                            )
                    )
                    
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(40.dp)
                            .background(
                                Color.White.copy(alpha = 0.1f),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "OR",
                            style = MaterialTheme.typography.caption.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                    
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.3f),
                                        Color.Transparent
                                    )
                                )
                            )
                    )
                }

                // Create account section
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Don't have an account?",
                        style = MaterialTheme.typography.body2,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                    
                    TextButton(
                        onClick = navigateToRegister,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(
                            text = "Create Account",
                            color = Color(0xFFD4A574),
                            style = MaterialTheme.typography.button.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
        }
        
        // Decorative floating elements
        repeat(3) { index ->
            val offsetY by infiniteTransition.animateFloat(
                initialValue = -50f,
                targetValue = 50f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 4000 + index * 1000,
                        easing = FastOutSlowInEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
            
            Box(
                modifier = Modifier
                    .offset(
                        x = when(index) {
                            0 -> 40.dp
                            1 -> 300.dp
                            else -> 180.dp
                        },
                        y = (100 + index * 150 + offsetY).dp
                    )
                    .size(80.dp)
                    .alpha(0.1f)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFB8860B),
                                Color.Transparent
                            ),
                            radius = 80f
                        )
                    )
            )
        }
    }
}

