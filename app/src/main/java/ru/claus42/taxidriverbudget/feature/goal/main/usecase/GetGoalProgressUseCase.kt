package ru.claus42.taxidriverbudget.feature.goal.main.usecase

import kotlinx.coroutines.flow.Flow
import ru.claus42.taxidriverbudget.domain.model.Money

interface GetGoalProgressUseCase {
    fun getGoalProgressUseCase(id: Int): Flow<Money>
}