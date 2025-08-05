package com.plcoding.ShopSphere.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


object GlobalToast {
    val state = ToastState()
}

class ToastState {
    private val _message = mutableStateOf<String?>(null)
    val message = _message
    fun show(message: String) {
        _message.value = message
    }

    fun clear() {
        _message.value = null
    }
}

@Composable
fun ToastHost(toastState: ToastState = GlobalToast.state) {
    val message = toastState.message.value

    if (message != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                elevation = 8.dp,
                backgroundColor = Color.Black.copy(alpha = 0.8f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = message,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
        }

        // Automatically dismiss after 2 seconds
        LaunchedEffect(message) {
            delay(2000)
            toastState.clear()
        }
    }
}