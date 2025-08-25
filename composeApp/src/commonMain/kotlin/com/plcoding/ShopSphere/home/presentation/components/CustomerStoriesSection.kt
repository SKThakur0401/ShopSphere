package com.plcoding.ShopSphere.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import com.plcoding.ShopSphere.home.data.dataModels.CustomerStories

@Composable
fun CustomerStoriesSection(customers: List<CustomerStories> = provideDummyCustomerStories()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Section
        Text(
            text = "Customer Stories",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 36.sp,
                lineHeight = 43.sp,
                letterSpacing = 0.5.sp
            ),
            color = Color(0xFF2C2C2C),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Divider
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(3.dp)
                .background(Color(0xFFD4A574))
                .padding(bottom = 24.dp)
        )
        
        // Subtitle
        Text(
            text = "Discover why our clients cherish their Carpet Boutique experience",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp
            ),
            color = Color(0xFF8B8B8B),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp)
        )
        
        // Testimonials Grid
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            customers.forEach { customer ->
                TestimonialCard(customer = customer)
            }
        }
    }
}

@Composable
private fun TestimonialCard(customer: CustomerStories) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Customer Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Customer Avatar
                CustomerAvatar(
                    imageUrl = customer.dpUrl,
                    customerName = customer.name
                )
                
                // Customer Info
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = customer.name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            lineHeight = 23.sp
                        ),
                        color = Color(0xFF2C2C2C)
                    )
                    
                    StarRating(rating = customer.rating)
                }
            }
            
            // Testimonial Text
            Text(
                text = "\"${customer.review}\"",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    lineHeight = 24.sp
                ),
                color = Color(0xFF6B6B6B),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CustomerAvatar(imageUrl: String, customerName: String) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFD4A574),
                        Color(0xFFC1956B)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        if (imageUrl.isNotEmpty()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "$customerName - Customer testimonial avatar",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            // Fallback with initials
            Text(
                text = getInitials(customerName),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                ),
                color = Color.White
            )
        }
    }
}

@Composable
private fun StarRating(rating: Double) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            val starValue = index + 1
            if (rating >= starValue) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color(0xFFE0E0E0),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

private fun getInitials(name: String): String {
    return name.split(" ")
        .take(2)
        .joinToString("") { it.firstOrNull()?.uppercase() ?: "" }
}

private fun provideDummyCustomerStories(): List<CustomerStories> = listOf(
    CustomerStories(
        customerId = 1L,
        name = "Sarah Johnson",
        rating = 5.0,
        review = "The Persian carpet I purchased transformed my living room. The quality is exceptional, and the colors are even more vibrant in person. The AR preview helped me choose the perfect size!",
        dpUrl = ""
    ),
    CustomerStories(
        customerId = 2L,
        name = "Michael Chen",
        rating = 5.0,
        review = "As an interior designer, I'm extremely particular about materials. The vintage silk kilim I sourced from Carpet Boutique exceeded my expectations. The craftsmanship is museum-quality.",
        dpUrl = ""
    ),
    CustomerStories(
        customerId = 3L,
        name = "Jennifer Martinez",
        rating = 4.5,
        review = "The customer service was outstanding. They helped me find the perfect modern geometric carpet for my open-plan space. It arrived perfectly packaged and exactly as shown online.",
        dpUrl = ""
    )
)
