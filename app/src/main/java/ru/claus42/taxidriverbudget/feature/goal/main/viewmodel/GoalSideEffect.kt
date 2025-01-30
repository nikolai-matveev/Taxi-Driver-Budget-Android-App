package ru.claus42.taxidriverbudget.feature.goal.main.viewmodel

sealed class GoalSideEffect {
    data class NavigateToEditGoalScreen(val id: Int) : GoalSideEffect()
    data object NavigateToAddGoalScreen : GoalSideEffect()
    data class ShowError(val errorMessage: String): GoalSideEffect()
}