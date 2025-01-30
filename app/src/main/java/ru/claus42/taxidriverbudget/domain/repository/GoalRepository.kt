package ru.claus42.taxidriverbudget.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.claus42.taxidriverbudget.domain.model.Goal

interface GoalRepository {
    suspend fun getGoalById(id: Int): Goal
    fun getGoalFlowById(id: Int): Flow<Goal>
    suspend fun insert(goal: Goal)
    suspend fun remove(goal: Goal)
    fun allGoalsFlow(): Flow<List<Goal>>
    suspend fun clearRepository()
}
