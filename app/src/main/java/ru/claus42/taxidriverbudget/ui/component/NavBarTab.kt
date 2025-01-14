package ru.claus42.taxidriverbudget.ui.component

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import ru.claus42.taxidriverbudget.navigation.Route
import ru.claus42.taxidriverbudget.R
import ru.claus42.taxidriverbudget.navigation.MainGraph
import ru.claus42.taxidriverbudget.ui.drawables.icon.IcoGoal
import ru.claus42.taxidriverbudget.ui.drawables.icon.IcoChart
import ru.claus42.taxidriverbudget.ui.drawables.icon.IcoFinance
import ru.claus42.taxidriverbudget.ui.drawables.icon.IcoSettings

sealed interface NavBarTab {
    val icon: ImageVector
    val labelResId: Int
    val route: Route
}

data object FinanceNavBarTab : NavBarTab {
    override val icon = IcoFinance
    override val labelResId = R.string.nav_tab_finance
    override val route = MainGraph.FinanceRoute
}

data object GoalNavBarTab : NavBarTab {
    override val icon = IcoGoal
    override val labelResId = R.string.nav_tab_goal
    override val route = MainGraph.GoalRoute
}


data object ChartNavBarTab : NavBarTab {
    override val icon = IcoChart
    override val labelResId = R.string.nav_tab_chart
    override val route = MainGraph.ChartRoute
}


data object SettingsNavBarTab : NavBarTab {
    override val icon = IcoSettings
    override val labelResId = R.string.nav_tab_settings
    override val route = MainGraph.SettingsRoute
}

val bottomNavBarTabs = listOf(
    FinanceNavBarTab,
    GoalNavBarTab,
    ChartNavBarTab,
    SettingsNavBarTab,
)

fun NavDestination?.toNavBarTab(): NavBarTab =
    bottomNavBarTabs.find { it.route::class.qualifiedName == this?.route } ?: FinanceNavBarTab