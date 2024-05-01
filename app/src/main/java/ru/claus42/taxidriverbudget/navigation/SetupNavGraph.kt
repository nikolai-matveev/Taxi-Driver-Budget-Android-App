package ru.claus42.taxidriverbudget.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.claus42.taxidriverbudget.HomeScreen
import ru.claus42.taxidriverbudget.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SPLASH.route,
    ) {
        composable(route = Screen.SPLASH.route) {
            SplashScreen {
                navController.navigate(Screen.HOME.route) {
                    popUpTo(Screen.SPLASH.route) { inclusive = true }
                }
            }
        }
        composable(route = Screen.HOME.route) {
            HomeScreen()
        }
    }
}