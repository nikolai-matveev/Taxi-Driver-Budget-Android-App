package ru.claus42.taxidriverbudget.feature.goal.main.viewmodel

import ru.claus42.taxidriverbudget.domain.model.Goal
import ru.claus42.taxidriverbudget.domain.model.Money

typealias GoalWithProgress = Pair<Goal, Money>

data class GoalScreenState(
    val goalsWithProgress: List<GoalWithProgress>,
    val isLoading: Boolean = false,
)