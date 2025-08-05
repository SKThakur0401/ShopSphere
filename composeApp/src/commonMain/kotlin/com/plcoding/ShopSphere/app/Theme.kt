package com.plcoding.ShopSphere.app

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color.kt - Define your color palette
val primaryColor = Color(0xFF8B5A2B)
val secondaryColor = Color(0xFFD2B48C)
val accentColor = Color(0xFFA0522D)
val lightBackground = Color(0xFFF5F5DC)
val darkText = Color(0xFF3E2723)

// Theme.kt - Custom theme definition
@Composable
fun CarpetBoutiqueTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = primaryColor,
            secondary = secondaryColor,
            background = lightBackground,
            surface = Color.White
        ),
        typography = Typography(
            h1 = TextStyle(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            ),
            h2 = TextStyle(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            ),
            body1 = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            button = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        ),
        shapes = Shapes(
            small = RoundedCornerShape(8.dp),
            medium = RoundedCornerShape(16.dp),
            large = RoundedCornerShape(24.dp)
        ),
        content = content
    )
}

