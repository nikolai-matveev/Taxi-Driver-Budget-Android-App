package ru.claus42.taxidriverbudget.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun RootNavHost(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SplashGraph
    ) {
        SplashNavGraph(navController)
        MainNavGraph()
    }
}