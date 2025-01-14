package ru.claus42.taxidriverbudget.utils

import androidx.navigation.NavHostController
import ru.claus42.taxidriverbudget.navigation.Route
import ru.claus42.taxidriverbudget.navigation.SubGraph

fun NavHostController.navigateSingleTopTo(subGraph: SubGraph, route: Route) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(subGraph) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }