package ru.claus42.taxidriverbudget.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.claus42.taxidriverbudget.feature.main.MainScreen


fun NavGraphBuilder.MainNavGraph() {
    composable<MainGraph>{
        MainScreen()
    }
}