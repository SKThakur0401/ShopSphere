package com.plcoding.ShopSphere.home.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.plcoding.ShopSphere.core.data.Constants
import com.plcoding.ShopSphere.core.presentation.SimpleShopSphereLogo
import kotlin.math.*


@Composable
fun HeroSection(
    modifier: Modifier = Modifier,
    onShopCollectionClick: () -> Unit = {},
    onExploreDesignsClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val infiniteTransition = rememberInfiniteTransition()
    
    // Floating animation for decorative elements
    val floatAnimation by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    // Subtle shimmer effect
    val shimmerAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A0F0A)) // Fallback color
    ) {
        // Stunning carpet background with subtle zoom effect
        AsyncImage(
            model = Constants.IMG_URL.HERO_SECTION,
            contentDescription = "Luxury carpet background",
            modifier = Modifier
                .fillMaxSize()
                .scale(1.05f), // Subtle zoom for depth
            contentScale = ContentScale.Crop
        )
        
        // Sophisticated gradient overlays
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0x66000000), // Top - semi-transparent
                            Color(0x44000000), // Middle - more transparent to show carpet
                            Color(0x88000000)  // Bottom - darker for text contrast
                        )
                    )
                )
        )
        
        // Radial overlay for spotlight effect
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0x33000000),
                            Color(0x66000000)
                        ),
                        radius = 1200f
                    )
                )
        )
        
        // Floating golden particles
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.4f)
        ) {
            repeat(12) { index ->
                val angle = (index * 30f + shimmerAnimation * 360f) * PI / 180
                val radius = 150f + (index % 3) * 80f
                val x = size.width / 2 + radius * cos(angle).toFloat()
                val y = size.height / 2 + radius * sin(angle).toFloat()
                
                if (x in 0f..size.width && y in 0f..size.height) {
                    drawCircle(
                        color = Color(0xFFB8860B).copy(alpha = 0.3f - (index % 3) * 0.08f),
                        radius = 4f - (index % 3) * 1f,
                        center = Offset(x, y)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            // Premium header with glass morphism
            PremiumHeroHeader(
                onSearchClick = onSearchClick,
                onCartClick = onCartClick,
                onProfileClick = onProfileClick,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .offset(y = (floatAnimation * 0.3f).dp)
            )

            Spacer(modifier = Modifier.weight(0.4f))

            // Luxury content section
            LuxuryHeroContent(
                onShopCollectionClick = onShopCollectionClick,
                onExploreDesignsClick = onExploreDesignsClick,
                floatAnimation = floatAnimation,
                modifier = Modifier.padding(bottom = 40.dp)
            )
            
            Spacer(modifier = Modifier.weight(0.1f))
        }
        
        // Floating decorative elements
        repeat(3) { index ->
            Box(
                modifier = Modifier
                    .offset(
                        x = when(index) {
                            0 -> 50.dp
                            1 -> 320.dp
                            else -> 200.dp
                        },
                        y = (150 + index * 200 + floatAnimation * (1 + index * 0.5f)).dp
                    )
                    .size(80.dp)
                    .alpha(0.1f)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFD4A574),
                                Color.Transparent
                            ),
                            radius = 80f
                        ),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
private fun PremiumHeroHeader(
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.15f)
        ),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Premium branding with logo
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SimpleShopSphereLogo(
                    logoSize = 36.dp,
                    color = Color(0xFFB8860B)
                )
                Column {
                    Text(
                        text = "ShopSphere",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp
                        ),
                        color = Color.White
                    )
                    Text(
                        text = "Luxury Carpets",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFFD4A574)
                    )
                }
            }

            // Elegant action buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PremiumActionButton(
                    icon = Icons.Outlined.Search,
                    onClick = onSearchClick,
                    contentDescription = "Search"
                )

                PremiumActionButton(
                    icon = Icons.Outlined.ShoppingCart,
                    onClick = onCartClick,
                    contentDescription = "Shopping cart"
                )

                PremiumActionButton(
                    icon = Icons.Outlined.Person,
                    onClick = onProfileClick,
                    contentDescription = "Profile"
                )
            }
        }
    }
}

@Composable
private fun PremiumActionButton(
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }
    
    Card(
        modifier = modifier
            .size(44.dp)
            .clickable { 
                isPressed = !isPressed
                onClick() 
            }
            .scale(if (isPressed) 0.95f else 1f),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.2f)
        ),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isPressed) 2.dp else 6.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun LuxuryHeroContent(
    onShopCollectionClick: () -> Unit,
    onExploreDesignsClick: () -> Unit,
    floatAnimation: Float,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = (floatAnimation * 0.5f).dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Luxury badge
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFB8860B).copy(alpha = 0.9f)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "✨ LUXURY COLLECTION ✨",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    ),
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // Sophisticated heading with enhanced typography
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Experience",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Light,
                        letterSpacing = 2.sp
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Timeless Elegance",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp
                    ),
                    color = Color(0xFFD4A574),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Underfoot",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 4.sp
                    ),
                    color = Color.White.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center
                )
                
                // Decorative underline
                Box(
                    modifier = Modifier
                        .width(100.dp)
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

            // Premium description
            Text(
                text = "Discover our exclusive collection of handcrafted Persian and Oriental carpets, where centuries-old artistry meets contemporary luxury. Each piece tells a story of tradition, elegance, and uncompromising quality.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Center
                ),
                color = Color.White.copy(alpha = 0.9f)
            )

            // Luxury action buttons with enhanced design
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Primary luxury button with gradient
                Button(
                    onClick = onShopCollectionClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB8860B)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 4.dp
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.Star,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "SHOP COLLECTION",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            letterSpacing = 1.sp
                        )
                    }
                }

                // Secondary elegant button
                OutlinedButton(
                    onClick = onExploreDesignsClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    border = BorderStroke(2.dp, Color(0xFFD4A574)),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = Color.White.copy(alpha = 0.1f)
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Outlined.Palette,
                            contentDescription = null,
                            tint = Color(0xFFD4A574),
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "EXPLORE DESIGNS",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
            
            // Trust indicators
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TrustIndicator(
                    icon = "🏆",
                    text = "Award\nWinning",
                    modifier = Modifier.weight(1f)
                )
                TrustIndicator(
                    icon = "🌟",
                    text = "Premium\nQuality",
                    modifier = Modifier.weight(1f)
                )
                TrustIndicator(
                    icon = "🚚",
                    text = "Free\nDelivery",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun TrustIndicator(
    icon: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = icon,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            ),
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}
