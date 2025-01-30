package ru.claus42.taxidriverbudget.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.claus42.taxidriverbudget.data.database.dao.GoalsDao
import ru.claus42.taxidriverbudget.data.database.entity.GoalEntity
import ru.claus42.taxidriverbudget.data.database.mapper.toDomainModel
import ru.claus42.taxidriverbudget.data.database.mapper.toEntity
import ru.claus42.taxidriverbudget.domain.model.Goal
import ru.claus42.taxidriverbudget.domain.repository.GoalRepository
import javax.inject.Inject

class GoalRepositoryImpl @Inject constructor(
    private val goalsDao: GoalsDao
) : GoalRepository {
    override suspend fun getGoalById(id: Int): Goal = goalsDao.getGoal(id).toDomainModel()
    override fun getGoalFlowById(id: Int): Flow<Goal> {
        return goalsDao.getGoalFlow(id).map(GoalEntity::toDomainModel)
    }

    override suspend fun insert(goal: Goal) = goalsDao.insertGoal(goal.toEntity())

    override suspend fun remove(goal: Goal) = goalsDao.deleteGoal(goal.toEntity())

    override fun allGoalsFlow(): Flow<List<Goal>> =
        goalsDao.getAllGoals().map { entityList ->
            entityList.map(GoalEntity::toDomainModel)
        }

    override suspend fun clearRepository() {
        goalsDao.clearTable()
    }

}
