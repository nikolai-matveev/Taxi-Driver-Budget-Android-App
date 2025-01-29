package ru.claus42.taxidriverbudget.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.claus42.taxidriverbudget.data.database.entity.GOALS_TABLE_NAME
import ru.claus42.taxidriverbudget.data.database.entity.GoalEntity

@Dao
interface GoalsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: GoalEntity)

    @Query("SELECT * FROM $GOALS_TABLE_NAME")
    fun getAllGoals(): Flow<List<GoalEntity>>

    @Delete
    suspend fun deleteGoal(goal: GoalEntity)
}