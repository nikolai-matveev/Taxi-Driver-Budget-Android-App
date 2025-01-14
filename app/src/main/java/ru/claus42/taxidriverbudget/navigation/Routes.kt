package ru.claus42.taxidriverbudget.navigation

import kotlinx.serialization.Serializable

sealed interface Route
sealed interface SubGraph


@Serializable
data object SplashGraph : SubGraph {
    @Serializable
    data object SplashRoute : Route
}

@Serializable
data object MainGraph : SubGraph {
    @Serializable
    data object FinanceRoute : Route

    @Serializable
    data object GoalRoute : Route

    @Serializable
    data object ChartRoute : Route

    @Serializable
    data object SettingsRoute : Route
}
