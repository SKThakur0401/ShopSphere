package com.plcoding.ShopSphere.core.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.*

@Composable
fun ShopSphereLogo(
    size: Dp = 80.dp,
    primaryColor: Color = Color(0xFFB8860B), // Gold
    secondaryColor: Color = Color(0xFFD4A574), // Light Gold
    backgroundColor: Color = Color.White
) {
    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(backgroundColor)
        ) {
            drawShopSphereLogo(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor
            )
        }
    }
}

private fun DrawScope.drawShopSphereLogo(
    primaryColor: Color,
    secondaryColor: Color
) {
    val center = Offset(size.width / 2f, size.height / 2f)
    val radius = size.minDimension / 2f * 0.8f
    
    // Draw outer decorative ring
    drawCircle(
        brush = Brush.sweepGradient(
            colors = listOf(
                primaryColor,
                secondaryColor,
                primaryColor,
                secondaryColor
            )
        ),
        radius = radius * 0.95f,
        center = center,
        style = Stroke(width = 4.dp.toPx())
    )
    
    // Draw carpet pattern - Persian/Oriental inspired
    drawCarpetPattern(center, radius * 0.8f, primaryColor, secondaryColor)
    
    // Draw inner circle with gradient
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(
                secondaryColor.copy(alpha = 0.3f),
                primaryColor.copy(alpha = 0.1f)
            ),
            radius = radius * 0.6f
        ),
        radius = radius * 0.6f,
        center = center
    )
    
    // Draw central "S" monogram
    drawMonogram(center, radius * 0.4f, primaryColor)
}

private fun DrawScope.drawCarpetPattern(
    center: Offset,
    radius: Float,
    primaryColor: Color,
    secondaryColor: Color
) {
    // Draw 8 ornamental petals around the center
    repeat(8) { index ->
        val angle = index * 45f * PI / 180f
        val petalCenter = Offset(
            center.x + radius * 0.6f * cos(angle).toFloat(),
            center.y + radius * 0.6f * sin(angle).toFloat()
        )
        
        // Draw petal shape
        val path = Path().apply {
            moveTo(
                petalCenter.x + 15f * cos(angle + PI/4).toFloat(),
                petalCenter.y + 15f * sin(angle + PI/4).toFloat()
            )
            quadraticBezierTo(
                petalCenter.x + 25f * cos(angle).toFloat(),
                petalCenter.y + 25f * sin(angle).toFloat(),
                petalCenter.x + 15f * cos(angle - PI/4).toFloat(),
                petalCenter.y + 15f * sin(angle - PI/4).toFloat()
            )
            quadraticBezierTo(
                petalCenter.x,
                petalCenter.y,
                petalCenter.x + 15f * cos(angle + PI/4).toFloat(),
                petalCenter.y + 15f * sin(angle + PI/4).toFloat()
            )
            close()
        }
        
        drawPath(
            path = path,
            brush = Brush.linearGradient(
                colors = listOf(primaryColor, secondaryColor),
                start = Offset(petalCenter.x - 20f, petalCenter.y - 20f),
                end = Offset(petalCenter.x + 20f, petalCenter.y + 20f)
            )
        )
    }
    
    // Draw inner geometric pattern
    repeat(4) { index ->
        val angle = index * 90f * PI / 180f
        drawLine(
            color = primaryColor.copy(alpha = 0.6f),
            start = Offset(
                center.x + radius * 0.3f * cos(angle).toFloat(),
                center.y + radius * 0.3f * sin(angle).toFloat()
            ),
            end = Offset(
                center.x + radius * 0.5f * cos(angle).toFloat(),
                center.y + radius * 0.5f * sin(angle).toFloat()
            ),
            strokeWidth = 2.dp.toPx()
        )
    }
}

private fun DrawScope.drawMonogram(
    center: Offset,
    radius: Float,
    color: Color
) {
    // Draw stylized "S" for ShopSphere
    val path = Path().apply {
        // Top curve of S
        moveTo(center.x - radius * 0.4f, center.y - radius * 0.6f)
        quadraticBezierTo(
            center.x + radius * 0.4f, center.y - radius * 0.8f,
            center.x + radius * 0.4f, center.y - radius * 0.2f
        )
        // Middle connection
        quadraticBezierTo(
            center.x - radius * 0.2f, center.y,
            center.x + radius * 0.2f, center.y
        )
        // Bottom curve of S
        quadraticBezierTo(
            center.x - radius * 0.4f, center.y + radius * 0.8f,
            center.x + radius * 0.4f, center.y + radius * 0.6f
        )
    }
    
    drawPath(
        path = path,
        color = color,
        style = Stroke(
            width = 8.dp.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )
}

// Alternative simple version
@Composable
fun SimpleShopSphereLogo(
    logoSize: Dp = 60.dp,
    color: Color = Color(0xFFB8860B)
) {
    Box(
        modifier = Modifier
            .size(logoSize)
            .clip(CircleShape)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        color,
                        color.copy(alpha = 0.8f),
                        color.copy(alpha = 0.6f)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val radius = size.minDimension / 2f * 0.3f
            
            // Draw simple carpet weave pattern
            repeat(3) { i ->
                repeat(3) { j ->
                    val x = center.x + (i - 1) * radius * 0.6f
                    val y = center.y + (j - 1) * radius * 0.6f
                    drawCircle(
                        color = Color.White.copy(alpha = 0.7f),
                        radius = radius * 0.15f,
                        center = Offset(x, y)
                    )
                }
            }
            
            // Draw central "S"
            drawCircle(
                color = Color.White,
                radius = radius * 0.8f,
                center = center,
                style = Stroke(width = 3.dp.toPx())
            )
        }
    }
}
