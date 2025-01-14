package ru.claus42.taxidriverbudget.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.claus42.taxidriverbudget.feature.chart.ChartScreen
import ru.claus42.taxidriverbudget.feature.goal.GoalScreen
import ru.claus42.taxidriverbudget.feature.finance.screen.main.FinanceScreen
import ru.claus42.taxidriverbudget.feature.settings.SettingsScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        route = MainGraph::class,
        startDestination = MainGraph.FinanceRoute,
        modifier = modifier
    ) {
        composable<MainGraph.FinanceRoute> {
            FinanceScreen()
        }

        composable<MainGraph.GoalRoute> {
            GoalScreen()
        }

        composable<MainGraph.ChartRoute> {
            ChartScreen()
        }

        composable<MainGraph.SettingsRoute> {
            SettingsScreen()
        }
    }
}