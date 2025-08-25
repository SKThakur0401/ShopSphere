package com.plcoding.ShopSphere.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun NewsletterSubscribeSection(onSubscribe: (String) -> Unit = {}) {
    val emailState = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = "https://jntbkxrqyjefnoerkmeu.supabase.co/storage/v1/object/public/PhotosOfCarpets/chair_explorable_img.png",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xCC8B4E2F), Color(0xCC8B4E2F))
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Join Our Exclusive\nCircle",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold,
                    lineHeight = 44.sp
                ),
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Subscribe to receive early access to new collections, special promotions, and interior design tips",
                style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 24.sp),
                color = Color.White.copy(alpha = 0.95f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = emailState.value,
                    onValueChange = { emailState.value = it },
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(topStart = 26.dp, bottomStart = 26.dp),
                    placeholder = { Text("Your email address") },
                    singleLine = true
                )
                Button(
                    onClick = { onSubscribe(emailState.value) },
                    shape = RoundedCornerShape(topEnd = 26.dp, bottomEnd = 26.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA0603B)),
                    modifier = Modifier
                        .height(56.dp)
                ) {
                    Text(
                        text = "Subscribe",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                        color = Color.White
                    )
                }
            }
        }
    }
}
