package com.plcoding.ShopSphere.app


import androidx.compose.runtime.*

import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.plcoding.ShopSphere.core.presentation.LoaderHost
import com.plcoding.ShopSphere.core.presentation.ToastHost
import com.plcoding.ShopSphere.home.presentation.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.plcoding.ShopSphere.login_signup.presentation.login.AuthViewModel
import com.plcoding.ShopSphere.login_signup.presentation.login.LoginScreenRoot
import com.plcoding.ShopSphere.login_signup.presentation.notes.NotesScreen
import com.plcoding.ShopSphere.login_signup.presentation.login.RegistrationScreenRoot
import com.plcoding.ShopSphere.login_signup.presentation.notes.NotesScreenRoot
import com.plcoding.ShopSphere.login_signup.presentation.notes.NotesViewModel

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
                composable<NavGraphA.SplashScreen> { backStackEntry ->
                    val viewModel  = backStackEntry.sharedKoinViewModel<AuthViewModel>(navController)
                    val gotoLoginPage ={navController.navigate(NavGraphA.LoginScreen){
                        popUpTo(NavGraphA.SplashScreen){inclusive = true}
                    } }

                    val gotoHomeScreen ={navController.navigate(NavGraphA.HomeScreen){
                        popUpTo(NavGraphA.SplashScreen){inclusive = true}
                    } }

                    SplashScreen(viewModel, gotoLoginPage, gotoHomeScreen)
                }

                composable<NavGraphA.LoginScreen> { backStackEntry ->
                    val viewModel  = backStackEntry.sharedKoinViewModel<AuthViewModel>(navController)
                    val navigateToRegister = {navController.navigate(NavGraphA.RegistrationScreen) }
                    val gotoNotesScreen = {navController.navigate(NavGraphA.practice)}
                    val onLoginSuccess = {navController.navigate(NavGraphA.HomeScreen){
                        popUpTo(NavGraphA.LoginScreen){ inclusive = true}
                    } }

                    LoginScreenRoot(viewModel, navigateToRegister, gotoNotesScreen, onLoginSuccess)
                }

                composable<NavGraphA.RegistrationScreen> { backStackEntry ->
                    val viewModel  = backStackEntry.sharedKoinViewModel<AuthViewModel>(navController)
                    val navigateToLogin : () -> Unit = {navController.navigateUp()}
                    // "navigateUp()" by default returns "Boolean" so this { } wrapping would've
                    // returned () -> Boolean       ---> But we don't want that, so we did
                    // explicit typecasting by mentioning to remove result by explicitly writing
                    // return type as () -> Unit

                    val onRegistrationSuccess = {navController.navigate(NavGraphA.HomeScreen){
                        popUpTo(NavGraphA.LoginScreen){ inclusive = true}
                    } }

                    RegistrationScreenRoot(viewModel, navigateToLogin, onRegistrationSuccess)
                }

                composable<NavGraphA.practice> {
                    val viewModel = koinViewModel<NotesViewModel>()
                    NotesScreenRoot(viewModel)
                }

                composable<NavGraphA.HomeScreen>{ backStackEntry ->
                    val viewModel  = backStackEntry.sharedKoinViewModel<AuthViewModel>(navController)
                    val onLogout = {navController.navigate(NavGraphA.LoginScreen){
                        popUpTo(NavGraphA.HomeScreen){inclusive = true }
                    } }
                    HomeScreen(onLogout, viewModel)
                }
            }
        }

        // Global toast overlay and global progress bar overlay which we will use app wide :)
        // The Two things below are overlayed on top of the nav-graph screen which is displayed
        // so these two below will be displayed on top of those
        ToastHost()
        LoaderHost()
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