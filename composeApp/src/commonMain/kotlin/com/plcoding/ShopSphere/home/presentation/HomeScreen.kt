package com.plcoding.ShopSphere.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.ShopSphere.home.presentation.components.HeroSection
import com.plcoding.ShopSphere.home.presentation.components.OurCollectionSection
import com.plcoding.ShopSphere.home.presentation.components.FeaturedCarpetsSection
import com.plcoding.ShopSphere.home.presentation.components.ARCarpetPreviewSection
import com.plcoding.ShopSphere.home.presentation.components.CustomerStoriesSection
import com.plcoding.ShopSphere.home.presentation.components.NewsletterSubscribeSection
import com.plcoding.ShopSphere.home.presentation.components.FooterSection
import com.plcoding.ShopSphere.login_signup.presentation.login.AuthState
import com.plcoding.ShopSphere.login_signup.presentation.login.AuthViewModel
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreenRoot(onLogoutNavigation: () -> Unit, authViewModel: AuthViewModel) {

    val authState by authViewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        logoutUser = {authViewModel.logoutUser()}
    )

    LaunchedEffect(authState.isSignedIn){
        if(authState.isSignedIn == false){
            onLogoutNavigation()
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(logoutUser : () -> Unit){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Box(modifier = Modifier.height(830.dp)) {
                    HeroSection(modifier = Modifier.fillMaxSize()) 
                }
            }

            item {
                OurCollectionSection()
            }

            item {
                FeaturedCarpetsSection()
                Spacer(Modifier.height(30.dp))
            }

            item {
                ARCarpetPreviewSection()
                Spacer(Modifier.height(30.dp))
            }

            item {
                CustomerStoriesSection()
                Spacer(Modifier.height(30.dp).background(Color.White))
            }

            item {
                NewsletterSubscribeSection()
            }

            item {
                FooterSection()
            }
        }
    }
}

