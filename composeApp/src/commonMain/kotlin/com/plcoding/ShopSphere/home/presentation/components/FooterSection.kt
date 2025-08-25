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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FooterSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3D2A25))
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        BrandBlock()
        DividerAccent()
        ShopBlock()
        DividerAccent()
        InfoBlock()
        DividerAccent()
        ContactBlock()
        DividerLight()
        CopyrightBlock()
    }
}

@Composable
private fun BrandBlock() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "Carpet Boutique",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            ),
            color = Color(0xFFF3E6DE)
        )
        AccentUnderline()
        Text(
            text = "Bringing timeless elegance and exceptional craftsmanship to homes worldwide since 2005.",
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
            color = Color(0xFFD9C9C2)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            listOf("f", "ig", "p", "yt").forEach { label ->
                SocialPill(text = label)
            }
        }
    }
}

@Composable
private fun ShopBlock() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SectionTitle(text = "Shop")
        FooterLink(text = "Persian Carpets")
        FooterLink(text = "Modern Designs")
        FooterLink(text = "Vintage Collection")
        FooterLink(text = "Custom Orders")
        FooterLink(text = "Clearance")
    }
}

@Composable
private fun InfoBlock() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SectionTitle(text = "Information")
        FooterLink(text = "About Us")
        FooterLink(text = "Craftsmanship")
        FooterLink(text = "Care Guide")
        FooterLink(text = "Shipping & Returns")
        FooterLink(text = "Privacy Policy")
    }
}

@Composable
private fun ContactBlock() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SectionTitle(text = "Contact")
        FooterInfoRow(emoji = "📍", text = "123 Design Avenue, New York, NY 10001")
        FooterInfoRow(emoji = "📞", text = "+1 (212) 555-7890")
        FooterInfoRow(emoji = "✉️", text = "info@carpetboutique.com")
        FooterInfoRow(emoji = "⏰", text = "Mon–Fri: 9AM–6PM, Sat: 10AM–4PM")
    }
}

@Composable
private fun CopyrightBlock() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "© 2025 Luxury Carpet Boutique. All rights reserved. Handcrafted with ❤️",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFD9C9C2),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun SectionTitle(text: String) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            color = Color(0xFFF3E6DE)
        )
        AccentUnderline()
    }
}

@Composable
private fun AccentUnderline() {
    Box(
        modifier = Modifier
            .size(width = 44.dp, height = 3.dp)
            .background(Color(0xFFD4A574))
    )
}

@Composable
private fun DividerAccent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFF5A3B33))
    )
}

@Composable
private fun DividerLight() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFF6D4A42))
    )
}

@Composable
private fun FooterLink(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = Color(0xFFD9C9C2)
    )
}

@Composable
private fun FooterInfoRow(emoji: String, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = emoji, modifier = Modifier.padding(end = 8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFD9C9C2)
        )
    }
}

@Composable
private fun SocialPill(text: String) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(Color(0xFF4B332D)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color(0xFF6E7BFF)
        )
    }
}
