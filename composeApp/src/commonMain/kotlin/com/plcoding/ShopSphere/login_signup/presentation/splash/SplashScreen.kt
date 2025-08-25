package com.plcoding.ShopSphere.login_signup.presentation.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.ShopSphere.app.accentColor
import com.plcoding.ShopSphere.app.darkText
import com.plcoding.ShopSphere.app.lightBackground
import com.plcoding.ShopSphere.app.primaryColor
import com.plcoding.ShopSphere.app.secondaryColor
import com.plcoding.ShopSphere.core.presentation.GlobalToast
import com.plcoding.ShopSphere.core.presentation.ShopSphereLogo
import com.plcoding.ShopSphere.core.domain.LogUtils
import com.plcoding.ShopSphere.login_signup.presentation.login.AuthViewModel
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.*

@Composable
fun SplashScreen(
    authViewModel: AuthViewModel,
    goToLoginScreen: () -> Unit,
    goToHomeScreen: () -> Unit) {

    val state by authViewModel.state.collectAsStateWithLifecycle()
    var hasNavigated by remember { mutableStateOf(false) }
    var hasCheckedAuth by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    state.error?.let {
        LaunchedEffect(it){
            GlobalToast.state.show(it)
        }
    }

    LaunchedEffect(Unit) {
        if (!hasCheckedAuth) {
            hasCheckedAuth = true
            delay(3000)
            authViewModel.checkSignInStatus()
        }
    }

    // Handle navigation based on authentication state
    LaunchedEffect(state.isSignedIn) {
        if (!hasNavigated && state.isSignedIn != null) {
            hasNavigated = true
            when (state.isSignedIn) {
                true -> goToHomeScreen()
                false -> goToLoginScreen()
                null -> { 
                    LogUtils.i("SplashScreen: Still loading, not navigating")
                }
            }
        } else {
            LogUtils.i("SplashScreen: Skipping navigation - hasNavigated: $hasNavigated, isSignedIn: ${state.isSignedIn}")
        }
    }

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
            ),
        contentAlignment = Alignment.Center
    ) {
        // Animated mandala pattern background
        val infiniteTransition = rememberInfiniteTransition()
        val rotation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(60000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
        
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f)
        ) {
            drawMandalaPattern(rotation)
        }
        
        // Glowing orbs background effect
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f)
        ) {
            repeat(3) { index ->
                val offsetAnimation by infiniteTransition.animateFloat(
                    initialValue = -200f,
                    targetValue = 200f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 8000 + (index * 2000),
                            easing = FastOutSlowInEasing
                        ),
                        repeatMode = RepeatMode.Reverse
                    )
                )
                
                Box(
                    modifier = Modifier
                        .offset(
                            x = (offsetAnimation * cos(index * 120f * PI / 180)).dp,
                            y = (offsetAnimation * sin(index * 120f * PI / 180)).dp
                        )
                        .size(300.dp)
                        .align(Alignment.Center)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFB8860B).copy(alpha = 0.4f),
                                    Color.Transparent
                                ),
                                radius = 300f
                            )
                        )
                )
            }
        }

        // Main content with animation
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .scale(scale)
                .alpha(scale)
        ) {
            // Logo card with elevation
            Card(
                modifier = Modifier
                    .size(160.dp)
                    .rotate(rotation / 4),
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.1f)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 16.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFB8860B),
                                    Color(0xFFD4A574)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    ShopSphereLogo(
                        size = 120.dp,
                        primaryColor = Color.White,
                        secondaryColor = Color.White.copy(alpha = 0.8f),
                        backgroundColor = Color.Transparent
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Brand name with gradient
            Text(
                text = "SHOPSPHERE",
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 4.sp
                ),
                modifier = Modifier.graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                },
                color = Color.White
            )

            Text(
                text = "Luxury Carpet Boutique",
                style = MaterialTheme.typography.h2.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 2.sp
                ),
                color = Color(0xFFD4A574)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Timeless Elegance Underfoot",
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 14.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                ),
                color = Color.White.copy(alpha = 0.7f)
            )
        }

        // Custom loading animation at bottom
        val loadingScale by infiniteTransition.animateFloat(
            initialValue = 0.8f,
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(3) { index ->
                    val delay = index * 200
                    val dotScale by infiniteTransition.animateFloat(
                        initialValue = 0.5f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(
                                durationMillis = 600,
                                delayMillis = delay,
                                easing = FastOutSlowInEasing
                            ),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                    
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .scale(dotScale)
                            .background(
                                Color(0xFFB8860B),
                                CircleShape
                            )
                    )
                }
            }
        }
    }
}

// Extension function to draw mandala pattern
private fun DrawScope.drawMandalaPattern(rotation: Float) {
    val centerX = size.width / 2
    val centerY = size.height / 2
    val maxRadius = minOf(size.width, size.height) / 2
    
    // Draw multiple layers of patterns
    for (layer in 0..4) {
        val radius = maxRadius * (0.2f + layer * 0.2f)
        val segments = 8 + layer * 4
        
        for (i in 0 until segments) {
            val angle = (360f / segments * i + rotation) * PI / 180
            val nextAngle = (360f / segments * (i + 1) + rotation) * PI / 180
            
            val x1 = centerX + radius * cos(angle).toFloat()
            val y1 = centerY + radius * sin(angle).toFloat()
            val x2 = centerX + radius * cos(nextAngle).toFloat()
            val y2 = centerY + radius * sin(nextAngle).toFloat()
            
            drawLine(
                color = Color(0xFFB8860B).copy(alpha = 0.3f - layer * 0.05f),
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = 2f
            )
            
            // Draw decorative circles
            drawCircle(
                color = Color(0xFFD4A574).copy(alpha = 0.2f),
                center = Offset(x1, y1),
                radius = 10f - layer * 2f
            )
        }
    }
}

