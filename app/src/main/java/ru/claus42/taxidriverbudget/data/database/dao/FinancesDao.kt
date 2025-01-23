package ru.claus42.taxidriverbudget.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.claus42.taxidriverbudget.data.database.entity.FinanceOperationEntity
import ru.claus42.taxidriverbudget.data.database.entity.OPERATION_DATE
import ru.claus42.taxidriverbudget.data.database.entity.OPERATION_ID
import ru.claus42.taxidriverbudget.data.database.entity.OPERATION_TABLE_NAME
import java.util.UUID

@Dao
interface FinancesDao {
    @Query("SELECT * FROM $OPERATION_TABLE_NAME WHERE $OPERATION_ID = :uuid LIMIT 1")
    fun getFinanceOperation(uuid: UUID): FinanceOperationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(operation: FinanceOperationEntity)

    @Delete
    fun delete(operation: FinanceOperationEntity)

    @Query(
        """
        SELECT * FROM $OPERATION_TABLE_NAME 
        WHERE $OPERATION_DATE BETWEEN :startDate AND :endDate
        ORDER BY $OPERATION_DATE DESC
        """
    )
    fun getFinanceOperationsInRangeFlow(startDate: String, endDate: String): Flow<List<FinanceOperationEntity>>

    @Query(
        """
        SELECT * FROM $OPERATION_TABLE_NAME 
        WHERE $OPERATION_DATE BETWEEN :startDate AND :endDate
        ORDER BY $OPERATION_DATE DESC
        """
    )
    fun getFinanceOperationsInRange(startDate: String, endDate: String): List<FinanceOperationEntity>
}