package ru.claus42.taxidriverbudget.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.claus42.taxidriverbudget.domain.model.Goal

interface GoalRepository {
    suspend fun add(goal: Goal)
    suspend fun remove(goal: Goal)
    fun allGoalsFlow(): Flow<List<Goal>>
}
