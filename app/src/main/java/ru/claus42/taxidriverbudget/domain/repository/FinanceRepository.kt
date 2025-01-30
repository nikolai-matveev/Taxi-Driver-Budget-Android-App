package ru.claus42.taxidriverbudget.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceOperation
import ru.claus42.taxidriverbudget.domain.model.Money
import java.time.ZonedDateTime

interface FinanceRepository {
    suspend fun add(financeOperation: FinanceOperation)
    suspend fun delete(financeOperation: FinanceOperation)
    fun getFinanceOperationsInRangeFlow(start: ZonedDateTime, end: ZonedDateTime): Flow<List<FinanceOperation>>
    suspend fun getOperationsInPeriod(start: ZonedDateTime, end: ZonedDateTime): List<FinanceOperation>
    fun calculateProfitForCurrency(start: ZonedDateTime, end: ZonedDateTime, currency: Currency): Flow<Money>
    suspend fun clearRepository()
}