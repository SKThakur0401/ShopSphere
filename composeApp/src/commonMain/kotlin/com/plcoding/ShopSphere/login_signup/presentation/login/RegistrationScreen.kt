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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.IconButton
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.ShopSphere.app.accentColor
import com.plcoding.ShopSphere.app.darkText
import com.plcoding.ShopSphere.app.lightBackground
import com.plcoding.ShopSphere.app.primaryColor
import com.plcoding.ShopSphere.app.secondaryColor
import com.plcoding.ShopSphere.core.presentation.GlobalToast
import com.plcoding.ShopSphere.login_signup.presentation.login.components.MyOutlinedTextField
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.*

@Composable
fun RegistrationScreenRoot(
    viewModel: AuthViewModel,
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
    viewModel: AuthViewModel
) {

    state.error?.let {
        LaunchedEffect(it){ // This prevents toast re-appearance coz of recomposition
            GlobalToast.state.show(it)      // Now this toast will be visible each time u press login
        }                               // and login fails... HOW??? Because each time u press
        // login, state.error becomes "NULL" because of Loading state and then it repopulates the
        // value with "Login Failed" hence there's a change in value and it appears each time
        // u press login with wrong credential... despite each time the text is same ;)
    }

    LaunchedEffect(state.isSignedIn){
        if(state.isSignedIn == true) onRegisterSuccess()
    }
    
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A0F0A),
                        Color(0xFF2D1810),
                        Color(0xFF1A0F0A)
                    )
                )
            )
    ) {
        // Animated wave pattern background
        val waveAnimation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(10000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.15f)
        ) {
            val width = size.width
            val height = size.height
            val waveHeight = 100f
            val waveLength = width / 3
            
            val path = Path()
            path.moveTo(0f, height / 2)
            
            for (x in 0..width.toInt() step 10) {
                val y = height / 2 + waveHeight * sin((x / waveLength + waveAnimation / 180) * PI).toFloat()
                path.lineTo(x.toFloat(), y)
            }
            
            path.lineTo(width, height)
            path.lineTo(0f, height)
            path.close()
            
            drawPath(
                path = path,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB8860B).copy(alpha = 0.3f),
                        Color.Transparent
                    )
                )
            )
            
            // Second wave
            val path2 = Path()
            path2.moveTo(0f, height / 2 + 50)
            
            for (x in 0..width.toInt() step 10) {
                val y = height / 2 + 50 + waveHeight * sin((x / waveLength + waveAnimation / 180 + 90) * PI).toFloat()
                path2.lineTo(x.toFloat(), y)
            }
            
            path2.lineTo(width, height)
            path2.lineTo(0f, height)
            path2.close()
            
            drawPath(
                path = path2,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFD4A574).copy(alpha = 0.2f),
                        Color.Transparent
                    )
                )
            )
        }

        // Floating glass card with enhanced design
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(32.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.15f),
                            Color.White.copy(alpha = 0.05f)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Progress indicators
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(3) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(
                                    if (index == 0) Color(0xFFB8860B) else Color.White.copy(alpha = 0.3f),
                                    CircleShape
                                )
                        )
                        if (index < 2) {
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
                
                // Welcome text
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.h2.copy(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Join our exclusive carpet community",
                    style = MaterialTheme.typography.body1,
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 24.dp),
                    textAlign = TextAlign.Center
                )

                // Name field
                OutlinedTextField(
                    value = state.name,
                    onValueChange = { viewModel.onAction(AuthActions.OnNameChange(it)) },
                    label = { Text("Full Name", color = Color.White.copy(alpha = 0.7f)) },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = "Name",
                            tint = Color(0xFFB8860B)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
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
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )
                
                // Email field
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { viewModel.onAction(AuthActions.OnEmailChange(it)) },
                    label = { Text("Email Address", color = Color.White.copy(alpha = 0.7f)) },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Email,
                            contentDescription = "Email",
                            tint = Color(0xFFB8860B)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
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
                
                // Password field
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
                        .padding(vertical = 6.dp),
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
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )

                // Confirm password field
                OutlinedTextField(
                    value = state.confirmPassword,
                    onValueChange = { viewModel.onAction(AuthActions.OnConfirmPasswordChange(it)) },
                    label = { Text("Confirm Password", color = Color.White.copy(alpha = 0.7f)) },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Lock,
                            contentDescription = "Confirm Password",
                            tint = Color(0xFFB8860B)
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { confirmPasswordVisible = !confirmPasswordVisible }
                        ) {
                            Icon(
                                imageVector = if (confirmPasswordVisible) Icons.Default.Star else Icons.Default.Lock,
                                contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password",
                                tint = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
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
                
                Spacer(modifier = Modifier.height(24.dp))

                // Animated register button
                val buttonAnimation by animateFloatAsState(
                    targetValue = if (state.isLoading) 0.95f else 1f,
                    animationSpec = spring(dampingRatio = 0.8f)
                )
                
                Button(
                    onClick = { viewModel.onAction(AuthActions.Register) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .scale(buttonAnimation),
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
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 2.dp,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    "Creating Account...",
                                    style = MaterialTheme.typography.button,
                                    color = Color.White
                                )
                            }
                    } else {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.PersonAdd,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    "Create Account",
                                    style = MaterialTheme.typography.button.copy(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))

                // Already have account section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already have an account?",
                        style = MaterialTheme.typography.body2,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                    
                    TextButton(
                        onClick = navigateToLogin
                    ) {
                        Text(
                            text = "Sign In",
                            color = Color(0xFFD4A574),
                            style = MaterialTheme.typography.button.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
        
        // Floating ornamental elements
        val floatAnimation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(3000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        
        // Top right ornament
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 50.dp, y = (-50).dp)
                .size(150.dp)
                .alpha(0.3f)
                .rotate(floatAnimation * 360f)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFB8860B),
                            Color.Transparent
                        )
                    ),
                    radius = size.minDimension / 2
                )
                
                // Draw ornamental pattern
                val center = Offset(size.width / 2, size.height / 2)
                repeat(8) { index ->
                    val angle = index * 45f * PI / 180
                    drawLine(
                        color = Color(0xFFD4A574).copy(alpha = 0.5f),
                        start = center,
                        end = Offset(
                            (center.x + 60 * cos(angle)).toFloat(),
                            (center.y + 60 * sin(angle)).toFloat()
                        ),
                        strokeWidth = 2.dp.toPx()
                    )
                }
            }
        }
        
        // Bottom left ornament
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = (-30).dp, y = 80.dp)
                .size(120.dp)
                .alpha(0.2f)
                .rotate(-floatAnimation * 180f)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val path = Path()
                val center = Offset(size.width / 2, size.height / 2)
                val radius = 50f
                
                // Draw star pattern
                repeat(6) { index ->
                    val angle = index * 60f * PI / 180
                    val x = center.x + radius * cos(angle).toFloat()
                    val y = center.y + radius * sin(angle).toFloat()
                    
                    if (index == 0) {
                        path.moveTo(x, y)
                    } else {
                        path.lineTo(x, y)
                    }
                }
                path.close()
                
                drawPath(
                    path = path,
                    color = Color(0xFFB8860B).copy(alpha = 0.3f),
                    style = Stroke(width = 2.dp.toPx())
                )
            }
        }
    }
}