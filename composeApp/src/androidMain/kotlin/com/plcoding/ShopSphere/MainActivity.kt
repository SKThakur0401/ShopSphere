package com.plcoding.ShopSphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.ShopSphere.app.App
import com.plcoding.ShopSphere.core.domain.LogUtils
import com.plcoding.ShopSphere.home.presentation.components.OurCollectionSection

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize logging
        LogUtils.setupLogging()
        LogUtils.i("MainActivity: Application started")

        setContent {
            App()
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun AppAndroidPreview() {

    OurCollectionSection()
}