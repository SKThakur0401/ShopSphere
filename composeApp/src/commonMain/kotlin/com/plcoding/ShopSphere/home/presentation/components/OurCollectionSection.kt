package com.plcoding.ShopSphere.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.ShopSphere.home.data.dataModels.Explorable
import coil3.compose.AsyncImage

@Composable
fun OurCollectionSection(items: List<Explorable> = provideDummyExplorables()): Unit {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 44.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Our Collections",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                lineHeight = 36.sp
            ),
            modifier = Modifier.padding(top = 50.dp, bottom = 50.dp),
            color = Color(0xFF3B2416),
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .height(3.dp)
                .background(Color(0xFFC17A49))
                .padding(horizontal = 0.dp)
                .fillMaxWidth(0.14f)
        )
        Text(
            text = "Explore our carefully curated carpet categories, each with its own unique story and craftsmanship",
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp),
            color = Color(0xFF6B6B6B),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items.forEach { item: Explorable ->
                ExplorableCard(item = item)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun ExplorableCard(item: Explorable): Unit {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = item.imgUrl,
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1f), // Use a 2:1 aspect ratio for a wider, less cropped image
                contentScale = ContentScale.Crop // Show the whole image, may add letterboxing
            )
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
                    .padding(start = 16.dp, end = 16.dp, top= 35.dp, bottom = 16.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = item.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(22.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB45F33))
                ) {
                    Text(text = "EXPLORE", color = Color.White)
                }
            }
        }
    }
}

private fun provideDummyExplorables(): List<Explorable> = listOf(
    Explorable(
        id = 1,
        title = "Persian Carpets",
        body = "Timeless designs with intricate patterns",
        imgUrl = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/chair_explorable_img.png"
    ),
    Explorable(
        id = 2,
        title = "Modern Carpets",
        body = "Contemporary designs for modern spaces",
        imgUrl = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/chair_explorable_img.png"
    ),
    Explorable(
        id = 3,
        title = "Vintage Carpets",
        body = "Authentic pieces with historical charm",
        imgUrl = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/chair_explorable_img.png"
    )
)