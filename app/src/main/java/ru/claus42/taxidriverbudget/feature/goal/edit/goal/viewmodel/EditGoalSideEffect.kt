package ru.claus42.taxidriverbudget.feature.goal.edit.goal.viewmodel

sealed class EditGoalSideEffect {
    data object AddNewGoal : EditGoalSideEffect()
}