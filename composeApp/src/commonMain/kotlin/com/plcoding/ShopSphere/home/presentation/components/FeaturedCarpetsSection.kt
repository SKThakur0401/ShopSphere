package com.plcoding.ShopSphere.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.plcoding.ShopSphere.home.data.dataModels.Product
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import kotlin.text.format

@Composable
fun FeaturedCarpetsSection(items: List<Product> = provideDummyProducts()): Unit {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 44.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Featured\nCarpets",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                lineHeight = 36.sp
            ),
            color = Color(0xFF3B2416),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp)
        )
        Box(
            modifier = Modifier
                .height(3.dp)
                .background(Color(0xFFC17A49))
                .fillMaxWidth(0.14f)
        )
        Text(
            text = "Handpicked masterpieces that combine exceptional craftsmanship with stunning aesthetics",
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp),
            color = Color(0xFF6B6B6B),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        items.forEach { product: Product ->
            ProductCard(product = product)
        }
    }
}

@Composable
private fun ProductCard(product: Product): Unit {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = product.productImgUrl,
                contentDescription = product.productName,
                modifier = Modifier.fillMaxWidth()
                    .aspectRatio(1f / 1f),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .background(Color(0xFFC17A49), RoundedCornerShape(24.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = product.tag.uppercase(),
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
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
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.productName,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "$${formatPrice(product.price)}",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                        color = Color(0xFFB45F33)
                    )
                    Text(
                        text = "$${formatPrice(product.price * 1.28)}",
                        style = MaterialTheme.typography.bodyMedium.copy(textDecoration = TextDecoration.LineThrough),
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
                        Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color(0xFFFFC107))
                    }
                    Text(
                        text = "  (${product.reviewCount} reviews)",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFE9C07A)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB45F33)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.height(0.dp))
                        Text(text = "  Add to Cart", color = Color.White)
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Box(
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(50))
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(modifier = Modifier.padding(10.dp)) {
                            Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null, tint = Color(0xFFB45F33))
                        }
                    }
                }
            }
        }
    }
}

private fun provideDummyProducts(): List<Product> = listOf(
    Product(
        productId = 101,
        productName = "Royal Persian Medallion",
        price = 1249.0,
        rating = 5.0,
        reviewCount = 42,
        productImgUrl = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/chair_explorable_img.png"
    ),
    Product(
        productId = 102,
        productName = "Royal Persian Medallion",
        price = 1249.0,
        rating = 5.0,
        reviewCount = 42,
        productImgUrl = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/chair_explorable_img.png"
    ),
    Product(
        productId = 103,
        productName = "Royal Persian Medallion",
        price = 1249.0,
        rating = 5.0,
        reviewCount = 42,
        productImgUrl = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/chair_explorable_img.png"
    )
)

private fun formatPrice(value: Double): String {
    val absValue: Double = kotlin.math.abs(value)
    return "%.0f".format(absValue).reversed().chunked(3).joinToString(",").reversed()
}


