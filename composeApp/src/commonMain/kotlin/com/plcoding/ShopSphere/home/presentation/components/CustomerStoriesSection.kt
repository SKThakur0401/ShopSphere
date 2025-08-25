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
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
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
import com.plcoding.ShopSphere.core.data.Constants

@Composable
fun CustomerStoriesSection(customers: List<CustomerStories> = provideDummyCustomerStories()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFAFAFA),
                        Color(0xFFF5F5F5)
                    )
                )
            )
            .padding(vertical = 40.dp)
    ) {
        // Header Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Badge
            Box(
                modifier = Modifier
                    .background(
                        Color(0xFFB8860B).copy(alpha = 0.1f),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "TESTIMONIALS",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 1.5.sp
                    ),
                    color = Color(0xFFB8860B)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "What Our",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Light,
                    fontSize = 32.sp
                ),
                color = Color(0xFF666666),
                textAlign = TextAlign.Center
            )
            
            Text(
                text = "Customers Say",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp
                ),
                color = Color(0xFF2D1810),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = "Join thousands of satisfied customers who've transformed their spaces",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp
                ),
                color = Color(0xFF888888),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // Testimonials Carousel
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.width(0.dp))
            customers.forEach { customer ->
                ModernTestimonialCard(customer = customer)
            }
            Spacer(modifier = Modifier.width(0.dp))
        }
        
        // Trust Indicators
        TrustIndicators()
    }
}

@Composable
private fun TrustIndicators() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TrustBadge(
            value = "4.9/5",
            label = "Average Rating",
            icon = "⭐"
        )
        
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(Color(0xFFE0E0E0))
        )
        
        TrustBadge(
            value = "10K+",
            label = "Happy Customers",
            icon = "😊"
        )
        
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(Color(0xFFE0E0E0))
        )
        
        TrustBadge(
            value = "99%",
            label = "Satisfaction",
            icon = "👍"
        )
    }
}

@Composable
private fun TrustBadge(
    value: String,
    label: String,
    icon: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = icon,
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFFB8860B)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            color = Color(0xFF2D1810)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 11.sp
            ),
            color = Color(0xFF888888)
        )
    }
}

@Composable
private fun ModernTestimonialCard(customer: CustomerStories) {
    Card(
        modifier = Modifier.width(320.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Rating Stars
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = if (index < customer.rating.toInt()) 
                                Color(0xFFFFC107) else Color(0xFFE0E0E0),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                
                // Testimonial Text
                Text(
                    text = "\"${customer.review}\"",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color(0xFF444444),
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Customer Info
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Avatar
                    ModernCustomerAvatar(
                        imageUrl = customer.dpUrl,
                        customerName = customer.name
                    )
                    
                    // Name and Verified Badge
                    Column {
                        Text(
                            text = customer.name,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            ),
                            color = Color(0xFF2D1810)
                        )
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(
                                        Color(0xFF4CAF50),
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "✓",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = Color.White
                                )
                            }
                            Text(
                                text = "Verified Buyer",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 12.sp
                                ),
                                color = Color(0xFF666666)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ModernCustomerAvatar(imageUrl: String, customerName: String) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
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
        if (imageUrl.isNotEmpty()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "$customerName - Customer avatar",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Text(
                text = getInitials(customerName),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
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
        dpUrl = Constants.IMG_URL.DP.SARAH
    ),
    CustomerStories(
        customerId = 2L,
        name = "Michael Chen",
        rating = 5.0,
        review = "As an interior designer, I'm extremely particular about materials. The vintage silk kilim I sourced from Carpet Boutique exceeded my expectations. The craftsmanship is museum-quality.",
        dpUrl = Constants.IMG_URL.DP.MICHAEL
    ),
    CustomerStories(
        customerId = 3L,
        name = "Jennifer Martinez",
        rating = 4.5,
        review = "The customer service was outstanding. They helped me find the perfect modern geometric carpet for my open-plan space. It arrived perfectly packaged and exactly as shown online.",
        dpUrl = Constants.IMG_URL.DP.JENIFER
    )
)
