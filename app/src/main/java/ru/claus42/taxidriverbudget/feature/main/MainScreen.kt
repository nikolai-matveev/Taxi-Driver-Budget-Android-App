package ru.claus42.taxidriverbudget.feature.main

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.claus42.taxidriverbudget.navigation.MainNavHost
import ru.claus42.taxidriverbudget.navigation.MainSubGraph
import ru.claus42.taxidriverbudget.ui.component.BottomNavBar
import ru.claus42.taxidriverbudget.ui.component.bottomNavBarTabs
import ru.claus42.taxidriverbudget.ui.component.toNavBarTab
import ru.claus42.taxidriverbudget.ui.utils.navigateSingleTopTo

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentTab = currentDestination.toNavBarTab()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                modifier = Modifier.navigationBarsPadding(),
                navBarTabs = bottomNavBarTabs,
                onTabSelected = { tab ->
                    navController.navigateSingleTopTo(
                        subGraph = MainSubGraph,
                        route = tab.route
                    )
                },
                currentTab = currentTab,
            )
        }
    ) { innerPadding ->
        MainNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}