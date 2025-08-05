package com.plcoding.ShopSphere.login_signup.presentation.splash

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plcoding.ShopSphere.app.accentColor
import com.plcoding.ShopSphere.app.darkText
import com.plcoding.ShopSphere.app.lightBackground
import com.plcoding.ShopSphere.app.primaryColor
import com.plcoding.ShopSphere.app.secondaryColor
import kotlinx.coroutines.delay


// SplashScreen.kt
@Composable
fun SplashScreen(goToLoginScreen: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    LaunchedEffect(Unit) {
        delay(2500)
        goToLoginScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBackground),
        contentAlignment = Alignment.Center
    ) {
        // Animated carpet pattern background
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val tileSize = 60.dp.toPx()

            for (x in 0..(width / tileSize).toInt()) {
                for (y in 0..(height / tileSize).toInt()) {
                    val color = when ((x + y) % 3) {
                        0 -> primaryColor.copy(alpha = 0.1f)
                        1 -> secondaryColor.copy(alpha = 0.1f)
                        else -> accentColor.copy(alpha = 0.1f)
                    }

                    drawRect(
                        color = color,
                        topLeft = Offset(x * tileSize, y * tileSize),
                        size = Size(tileSize, tileSize)
                    )
                }
            }
        }

        // Main logo with scale animation
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.scale(scale)
        ) {
            Icon(
                imageVector = Icons.Default.Star, // Use your carpet icon
                contentDescription = "App Logo",
                tint = primaryColor,
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Luxury Carpet Boutique",
                style = MaterialTheme.typography.h1,
                color = darkText,
                modifier = Modifier.alpha(0.9f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Timeless elegance underfoot",
                style = MaterialTheme.typography.body1,
                color = darkText.copy(alpha = 0.7f)
            )
        }

        // Loading indicator at bottom
        CircularProgressIndicator(
            color = accentColor,
            strokeWidth = 3.dp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
        )
    }
}

