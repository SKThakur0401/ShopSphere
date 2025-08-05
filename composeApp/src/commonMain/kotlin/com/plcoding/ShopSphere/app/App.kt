package com.plcoding.ShopSphere.app


import androidx.compose.runtime.*

import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.plcoding.ShopSphere.login_signup.presentation.login.AuthViewModel
import com.plcoding.ShopSphere.login_signup.presentation.login.LoginScreenRoot
import com.plcoding.ShopSphere.login_signup.presentation.login.NotesScreen
import com.plcoding.ShopSphere.login_signup.presentation.login.RegistrationScreenRoot
import com.plcoding.ShopSphere.login_signup.presentation.splash.SplashScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    CarpetBoutiqueTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = NavGraphA.Root
        ){
            navigation<NavGraphA.Root>(
                startDestination = NavGraphA.SplashScreen
            ){
                composable<NavGraphA.SplashScreen> {
                    val gotoLoginPage ={navController.navigate(NavGraphA.LoginScreen){
                        popUpTo(NavGraphA.SplashScreen){inclusive = true}
                    } }
                    SplashScreen(gotoLoginPage)
                }

                composable<NavGraphA.LoginScreen> {
                    val viewModel  = koinViewModel<AuthViewModel>()
                    val navigateToRegister = {navController.navigate(NavGraphA.RegistrationScreen) }
                    val gotoNotesScreen = {navController.navigate(NavGraphA.practice)}

                    LoginScreenRoot(viewModel, navigateToRegister, gotoNotesScreen) {}
                }

                composable<NavGraphA.RegistrationScreen> {

                    val viewModel  = koinViewModel<AuthViewModel>()
                    val navigateToLogin : () -> Unit = {navController.navigateUp()}
                    // "navigateUp()" by default returns "Boolean" so this { } wrapping would've
                    // returned () -> Boolean       ---> But we don't want that, so we did
                    // explicit typecasting by mentioning to remove result by explicitly writing
                    // return type as () -> Unit
                    RegistrationScreenRoot(viewModel, navigateToLogin) {}
                }

                composable<NavGraphA.practice> {
                    NotesScreen()
                }
            }
        }
    }
}


@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    // destination.parent ---> Implies the NavGraph

    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(                       // We're basically creating a koinViewModel only
        viewModelStoreOwner = parentEntry       // just changing the "viewModelStoreOwner" to "parentEntry"
    )                               // & to obtain "parentEntry" all this code was required
}                       // All this "NavBackStackEntry" & "navController" is just required to generate
// this "parentEntry" object :)