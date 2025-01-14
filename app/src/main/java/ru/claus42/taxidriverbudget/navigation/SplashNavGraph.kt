package ru.claus42.taxidriverbudget.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.claus42.taxidriverbudget.feature.splash.SplashScreen

fun NavGraphBuilder.SplashNavGraph(
    navController: NavHostController
) {
    navigation<SplashGraph>(
        startDestination = SplashGraph.SplashRoute
    ) {
        composable<SplashGraph.SplashRoute> {
            SplashScreen {
                navController.navigate(MainGraph) {
                    popUpTo(SplashGraph) { inclusive = true }
                }
            }
        }
    }
}