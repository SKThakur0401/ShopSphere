package com.plcoding.ShopSphere.home.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kotlin.math.*

@Composable
fun NewsletterSubscribeSection(onSubscribe: (String) -> Unit = {}) {
    val emailState = remember { mutableStateOf("") }
    val infiniteTransition = rememberInfiniteTransition()
    var isHovered by remember { mutableStateOf(false) }
    
    // Floating animation for decorative elements
    val floatAnimation by infiniteTransition.animateFloat(
        initialValue = -20f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    // Particle animation
    val particleOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        // Enhanced background with gradient overlay
        AsyncImage(
            model = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/chair_explorable_img.png",
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(1.1f), // Subtle zoom effect
            contentScale = ContentScale.Crop
        )
        
        // Animated gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0x88000000),
                            Color(0xCC1A0F0A),
                            Color(0xEE1A0F0A)
                        ),
                        radius = 800f
                    )
                )
        )
        
        // Floating particles background
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f)
        ) {
            repeat(12) { index ->
                val angle = (index * 30f + particleOffset) * PI / 180
                val radius = 200f + (index % 3) * 100f
                val x = size.width / 2 + radius * cos(angle).toFloat()
                val y = size.height / 2 + radius * sin(angle).toFloat()
                
                if (x in 0f..size.width && y in 0f..size.height) {
                    drawCircle(
                        color = Color(0xFFB8860B).copy(alpha = 0.4f - (index % 3) * 0.1f),
                        radius = 8f - (index % 3) * 2f,
                        center = Offset(x, y)
                    )
                }
            }
        }

        // Main content in glass morphism card
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.1f)
            ),
            shape = RoundedCornerShape(32.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Premium badge
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFB8860B).copy(alpha = 0.9f)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "✨ EXCLUSIVE ACCESS ✨",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        ),
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                    )
                }
                
                // Main heading with enhanced typography
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Join Our Luxury",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Light,
                            letterSpacing = 2.sp
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Circle",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 3.sp
                        ),
                        color = Color(0xFFD4A574),
                        textAlign = TextAlign.Center
                    )
                    
                    // Decorative underline
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(3.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color(0xFFB8860B),
                                        Color.Transparent
                                    )
                                )
                            )
                    )
                }

                Text(
                    text = "Subscribe to receive early access to new collections, exclusive promotions, and expert interior design insights.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 28.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.White.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center
                )

                // Enhanced subscription form
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = emailState.value,
                            onValueChange = { emailState.value = it },
                            modifier = Modifier.weight(1f),
                            placeholder = {
                                Text(
                                    "Enter your email address",
                                    color = Color.White.copy(alpha = 0.6f)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Email,
                                    contentDescription = null,
                                    tint = Color(0xFFB8860B)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color(0xFFB8860B),
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedLabelColor = Color(0xFFB8860B),
                                unfocusedLabelColor = Color.White.copy(alpha = 0.7f)
                            ),
                            shape = RoundedCornerShape(20.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        Button(
                            onClick = { 
                                if (emailState.value.isNotBlank()) {
                                    onSubscribe(emailState.value)
                                    emailState.value = ""
                                }
                            },
                            modifier = Modifier
                                .height(56.dp)
                                .clickable { isHovered = !isHovered },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFB8860B)
                            ),
                            shape = RoundedCornerShape(20.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = if (isHovered) 8.dp else 4.dp
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    Icons.Default.Send,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    "Subscribe",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
                
                // Benefits row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BenefitItem(icon = "🎯", text = "Early Access", modifier = Modifier.weight(1f))
                    BenefitItem(icon = "💎", text = "Exclusive Deals", modifier = Modifier.weight(1f))
                    BenefitItem(icon = "🎨", text = "Design Tips", modifier = Modifier.weight(1f))
                }
            }
        }
        
        // Floating decorative elements
        repeat(3) { index ->
            Box(
                modifier = Modifier
                    .offset(
                        x = when(index) {
                            0 -> 30.dp
                            1 -> 320.dp
                            else -> 180.dp
                        },
                        y = (60 + index * 120 + floatAnimation).dp
                    )
                    .size(60.dp)
                    .alpha(0.15f)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFD4A574),
                                Color.Transparent
                            ),
                            radius = 60f
                        ),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
private fun BenefitItem(
    icon: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.1f)
            ),
            shape = CircleShape,
            modifier = Modifier.size(48.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = icon,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium
            ),
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
    }
}
