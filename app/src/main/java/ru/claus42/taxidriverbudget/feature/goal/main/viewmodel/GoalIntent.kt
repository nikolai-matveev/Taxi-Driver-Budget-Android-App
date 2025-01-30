package ru.claus42.taxidriverbudget.feature.goal.main.viewmodel

sealed class GoalIntent {
    data object AddGoalClicked: GoalIntent()
    data class EditGoalClicked(val id: Int): GoalIntent()
}