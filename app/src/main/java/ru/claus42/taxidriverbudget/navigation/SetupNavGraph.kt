package ru.claus42.taxidriverbudget.navigation

import android.R
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.claus42.taxidriverbudget.feature.income.IncomeScreen
import ru.claus42.taxidriverbudget.SplashScreen
import ru.claus42.taxidriverbudget.feature.chart.ChartScreen
import ru.claus42.taxidriverbudget.feature.goal.GoalScreen
import ru.claus42.taxidriverbudget.feature.settings.SettingsScreen

@Composable
fun ComponentActivity.SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Splash,
    ) {
        composable<Splash> {
            SplashScreen {
                navController.navigate(Income) {
                    popUpTo(Splash) { inclusive = true }
                }
            }
        }

        composable<Income> {
            IncomeScreen()
        }

        composable<Goal> {
            GoalScreen()
        }

        composable<Chart> {
            ChartScreen()
        }

        composable<Settings> {
            SettingsScreen()
        }
    }
}