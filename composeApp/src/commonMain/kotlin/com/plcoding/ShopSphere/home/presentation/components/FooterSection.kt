package com.plcoding.ShopSphere.home.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.ShopSphere.core.presentation.SimpleShopSphereLogo
import kotlin.math.*

@Composable
fun FooterSection() {
    val infiniteTransition = rememberInfiniteTransition()
    
    // Floating animation for decorative elements
    val floatAnimation by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    // Particle rotation animation
    val rotationAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(30000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
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
        // Animated background pattern
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.1f)
        ) {
            repeat(20) { index ->
                val angle = (index * 18f + rotationAnimation) * PI / 180
                val radius = (index % 4 + 1) * 100f
                val x = size.width / 2 + radius * cos(angle).toFloat()
                val y = size.height / 2 + radius * sin(angle).toFloat()
                
                if (x in 0f..size.width && y in 0f..size.height) {
                    drawCircle(
                        color = Color(0xFFB8860B).copy(alpha = 0.3f - (index % 4) * 0.05f),
                        radius = 6f - (index % 4) * 1f,
                        center = Offset(x, y)
                    )
                }
            }
        }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Premium Brand Section
            PremiumBrandSection()
            
            // Main content grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    ModernShopSection()
                }
                Column(modifier = Modifier.weight(1f)) {
                    ModernInfoSection()
                }
            }
            
            // Contact section with enhanced design
            ModernContactSection()
            
            // Newsletter integration
            FooterNewsletterSection()
            
            // Elegant divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0xFFB8860B).copy(alpha = 0.5f),
                                Color.Transparent
                            )
                        )
                    )
            )
            
            // Enhanced copyright section
            PremiumCopyrightSection()
        }
        
        // Floating decorative elements
        repeat(2) { index ->
            Box(
                modifier = Modifier
                    .offset(
                        x = (if (index == 0) 50.dp else 300.dp),
                        y = (100 + index * 200 + floatAnimation).dp
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
private fun PremiumBrandSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Brand logo and name
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFB8860B).copy(alpha = 0.1f)
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SimpleShopSphereLogo(
                    logoSize = 40.dp,
                    color = Color(0xFFB8860B)
                )
                Text(
                    text = "ShopSphere",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp
                    ),
                    color = Color.White
                )
            }
        }
        
        Text(
            text = "Luxury Carpet Boutique",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Light,
                letterSpacing = 3.sp
            ),
            color = Color(0xFFD4A574)
        )
        
        Text(
            text = "Bringing timeless elegance and exceptional craftsmanship to homes worldwide. Each carpet tells a story of tradition, artistry, and uncompromising quality.",
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 26.sp,
                textAlign = TextAlign.Center
            ),
            color = Color.White.copy(alpha = 0.8f),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        
        // Enhanced social media section
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            listOf(
                Pair(Icons.Outlined.Share, "Facebook"),
                Pair(Icons.Outlined.Star, "Instagram"),
                Pair(Icons.Outlined.Favorite, "Pinterest"),
                Pair(Icons.Outlined.PlayArrow, "YouTube")
            ).forEach { (icon, label) ->
                ModernSocialButton(icon = icon, label = label)
            }
        }
    }
}

@Composable
private fun ModernSocialButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String
) {
    var isPressed by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier
            .size(50.dp)
            .clickable { isPressed = !isPressed }
            .scale(if (isPressed) 0.9f else 1f),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFB8860B).copy(alpha = 0.2f)
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
                contentDescription = label,
                tint = Color(0xFFD4A574),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun ModernShopSection() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.05f)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    Icons.Outlined.ShoppingCart,
                    contentDescription = null,
                    tint = Color(0xFFB8860B),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Shop Collections",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
            }
            
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(3.dp)
                    .background(Color(0xFFB8860B))
            )
            
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf(
                    "Persian Carpets",
                    "Modern Designs", 
                    "Vintage Collection",
                    "Custom Orders",
                    "Clearance Sale"
                ).forEach { item ->
                    ModernFooterLink(text = item)
                }
            }
        }
    }
}

@Composable
private fun ModernInfoSection() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.05f)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    Icons.Outlined.Info,
                    contentDescription = null,
                    tint = Color(0xFFB8860B),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Information",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
            }
            
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(3.dp)
                    .background(Color(0xFFB8860B))
            )
            
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf(
                    "About Us",
                    "Craftsmanship",
                    "Care Guide",
                    "Shipping & Returns",
                    "Privacy Policy"
                ).forEach { item ->
                    ModernFooterLink(text = item)
                }
            }
        }
    }
}

@Composable
private fun ModernContactSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.05f)
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    Icons.Outlined.Phone,
                    contentDescription = null,
                    tint = Color(0xFFB8860B),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Get in Touch",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ModernContactItem(
                        icon = Icons.Outlined.LocationOn,
                        title = "Address",
                        content = "123 Design Avenue\nNew York, NY 10001"
                    )
                    ModernContactItem(
                        icon = Icons.Outlined.Phone,
                        title = "Phone",
                        content = "+1 (212) 555-7890"
                    )
                }
                
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ModernContactItem(
                        icon = Icons.Outlined.Email,
                        title = "Email",
                        content = "info@shopsphere.com"
                    )
                    ModernContactItem(
                        icon = Icons.Outlined.Info,
                        title = "Hours",
                        content = "Mon–Fri: 9AM–6PM\nSat: 10AM–4PM"
                    )
                }
            }
        }
    }
}

@Composable
private fun FooterNewsletterSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFB8860B).copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color(0xFFB8860B).copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Stay Connected",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            Text(
                text = "Follow us for the latest collections and design inspiration",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun PremiumCopyrightSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Terms of Service",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFD4A574),
                modifier = Modifier.clickable { }
            )
            Text(
                text = "•",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.5f)
            )
            Text(
                text = "Privacy Policy",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFD4A574),
                modifier = Modifier.clickable { }
            )
            Text(
                text = "•",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.5f)
            )
            Text(
                text = "Cookies",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFD4A574),
                modifier = Modifier.clickable { }
            )
        }
        
        Text(
            text = "© 2025 ShopSphere Luxury Carpets. All rights reserved. Handcrafted with ❤️ in New York",
            style = MaterialTheme.typography.bodySmall.copy(
                textAlign = TextAlign.Center
            ),
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ModernFooterLink(text: String) {
    var isPressed by remember { mutableStateOf(false) }
    
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = if (isPressed) Color(0xFFD4A574) else Color.White.copy(alpha = 0.8f),
        modifier = Modifier
            .clickable { isPressed = !isPressed }
            .padding(vertical = 4.dp)
    )
}

@Composable
private fun ModernContactItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    content: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFB8860B).copy(alpha = 0.2f)
            ),
            shape = CircleShape,
            modifier = Modifier.size(40.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color(0xFFD4A574),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color(0xFFD4A574)
            )
            Text(
                text = content,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.8f),
                lineHeight = 18.sp
            )
        }
    }
}
