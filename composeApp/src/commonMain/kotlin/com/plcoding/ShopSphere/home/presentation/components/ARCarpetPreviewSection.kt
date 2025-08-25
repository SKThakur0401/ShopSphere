package com.plcoding.ShopSphere.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun ARCarpetPreviewSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFF5F2E8),
                        Color(0xFFF5F2E8),
                        Color(0xFFD4B896),
                        Color(0xFFD4B896)
                    )
                )
            )
            .padding(horizontal = 24.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hero Section
        Text(
            text = "See It In Your Space",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 32.sp,
                lineHeight = 38.sp,
                letterSpacing = 0.5.sp
            ),
            color = Color(0xFF3A2F26),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        Text(
            text = "Our augmented reality feature allows you to visualize how our carpets will look in your own home before making a purchase.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            color = Color(0xFF6B5B52),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        
        // Features Section
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier.padding(bottom = 48.dp)
        ) {
            FeatureItem(
                iconText = "📷",
                title = "Easy to Use",
                description = "Simply point your smartphone camera at your floor space to preview carpets."
            )
            
            FeatureItem(
                iconText = "📏",
                title = "Accurate Sizing",
                description = "Adjust dimensions to ensure perfect fit for your room."
            )
            
            FeatureItem(
                iconText = "🎨",
                title = "Color Matching",
                description = "See how different colors complement your existing decor."
            )
        }
        
        // CTA Button
        Button(
            onClick = { /* TODO: Implement AR preview functionality */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFA0603B)
            ),
            shape = RoundedCornerShape(24.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                horizontal = 32.dp,
                vertical = 16.dp
            )
        ) {
            Text(
                text = "TRY AR PREVIEW",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    letterSpacing = 1.sp
                ),
                color = Color(0xFFF5F2E8)
            )
        }
        
        // Product Preview Card
        ProductPreviewCard()
    }
}

@Composable
private fun FeatureItem(
    iconText: String,
    title: String,
    description: String
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Icon Container
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = Color(0xFFA0603B),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = iconText,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                color = Color(0xFFF5F2E8)
            )
        }
        
        // Content
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                ),
                color = Color(0xFF3A2F26),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                ),
                color = Color(0xFF6B5B52)
            )
        }
    }
}

@Composable
private fun ProductPreviewCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Product Image
            AsyncImage(
                model = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/chair_explorable_img.png",
                contentDescription = "Persian Medallion in Modern Living Room",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            
            // Gradient Overlay
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
            )
            
            // Product Info Overlay
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Persian Medallion in Modern Living Room",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    ),
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                
                Text(
                    text = "8' x 10' Hand-knotted Wool",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}
