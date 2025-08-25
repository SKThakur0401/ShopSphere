package com.plcoding.ShopSphere.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// ✅ Fixed imports - remove duplicate and fix package structure
// ✅ Correct imports - no package prefix needed
import coil3.compose.AsyncImage
import com.plcoding.ShopSphere.core.data.Constants


@Composable
fun HeroSection(
    modifier: Modifier = Modifier,
    onShopCollectionClick: () -> Unit = {},
    onExploreDesignsClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Background carpet image
        // ✅ Try this direct approach first

       AsyncImage(
           model = Constants.IMG_URL.HERO_SECTION,
           contentDescription = "Carpet background",
           modifier = Modifier.matchParentSize(),
           contentScale = ContentScale.Crop
       )


        // Dark overlay for better text readability
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startX = 0f,
                        endX = Float.POSITIVE_INFINITY
                    )
                )
        )

        Column(
            modifier = Modifier
                .matchParentSize()
        ) {
            // Top header section
            HeroHeader(
                onSearchClick = onSearchClick,
                onCartClick = onCartClick,
                onProfileClick = onProfileClick,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Content section
            HeroContent(
                onShopCollectionClick = onShopCollectionClick,
                onExploreDesignsClick = onExploreDesignsClick,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 40.dp)
            )
        }
    }
}

@Composable
private fun HeroHeader(
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Brand name
        Text(
            text = "Carpet\nBoutique",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp
            ),
            color = Color(0xFF8B4513) // Brown color from your design
        )

        // Action buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeroActionButton(
                icon = Icons.Default.Search,
                onClick = onSearchClick,
                contentDescription = "Search"
            )

            HeroActionButton(
                icon = Icons.Default.ShoppingCart,
                onClick = onCartClick,
                contentDescription = "Shopping cart"
            )

            HeroActionButton(
                icon = Icons.Default.Person,
                onClick = onProfileClick,
                contentDescription = "Profile"
            )
        }
    }
}

@Composable
private fun HeroActionButton(
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(44.dp)
            .background(
                color = Color.White,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun HeroContent(
    onShopCollectionClick: () -> Unit,
    onExploreDesignsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Container label
        Text(
            text = "Container",
            style = MaterialTheme.typography.labelMedium,
            color = Color.White.copy(alpha = 0.8f)
        )

        // Main heading
        Text(
            text = "Experience\nTimeless\nElegance\nUnderfoot",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
                lineHeight = 56.sp
            ),
            color = Color.White
        )

        // Description
        Text(
            text = "Discover our exclusive collection of handcrafted carpets that transform your space into a masterpiece of comfort and style.",
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 24.sp
            ),
            color = Color.White.copy(alpha = 0.9f),
            modifier = Modifier.padding(end = 40.dp)
        )

        // Action buttons
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Primary button
            Button(
                onClick = onShopCollectionClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB8860B) // Golden brown
                ),
                shape = RoundedCornerShape(26.dp)
            ) {
                Text(
                    text = "SHOP COLLECTION",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.White
                )
            }

            // Secondary button
            OutlinedButton(
                onClick = onExploreDesignsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                border = BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "EXPLORE DESIGNS",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.White
                )
            }
        }
    }
}
