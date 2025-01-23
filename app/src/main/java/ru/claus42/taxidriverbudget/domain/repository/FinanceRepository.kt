package ru.claus42.taxidriverbudget.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.claus42.taxidriverbudget.data.database.entity.FinanceOperationEntity
import ru.claus42.taxidriverbudget.domain.model.FinanceOperation
import java.time.ZonedDateTime

interface FinanceRepository {
    suspend fun add(financeOperation: FinanceOperation)
    suspend fun delete(financeOperation: FinanceOperation)
    fun getFinanceOperationsInRangeFlow(start: ZonedDateTime, end: ZonedDateTime): Flow<List<FinanceOperation>>
    suspend fun getOperationsInPeriod(start: ZonedDateTime, end: ZonedDateTime): List<FinanceOperation>
}