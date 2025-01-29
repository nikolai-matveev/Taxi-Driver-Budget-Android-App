package ru.claus42.taxidriverbudget.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.claus42.taxidriverbudget.data.database.dao.FinancesDao
import ru.claus42.taxidriverbudget.data.database.entity.FinanceOperationEntity
import ru.claus42.taxidriverbudget.data.database.mapper.toDomainModel
import ru.claus42.taxidriverbudget.data.database.mapper.toEntity
import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceOperation
import ru.claus42.taxidriverbudget.domain.model.Money
import ru.claus42.taxidriverbudget.domain.repository.FinanceRepository
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FinanceRepositoryImpl @Inject constructor(
    private val financesDao: FinancesDao
) : FinanceRepository {
    override suspend fun add(financeOperation: FinanceOperation) {
        financesDao.insert(financeOperation.toEntity())
    }

    override suspend fun delete(financeOperation: FinanceOperation) {
        financesDao.delete(financeOperation.toEntity())
    }

    override fun getFinanceOperationsInRangeFlow(
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Flow<List<FinanceOperation>> {

        val startDate = start.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
        val endDate = end.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)

        return financesDao
            .getFinanceOperationsInRangeFlow(startDate, endDate)
            .map { entities ->
                entities.map(FinanceOperationEntity::toDomainModel)
            }
    }

    override suspend fun getOperationsInPeriod(
        start: ZonedDateTime,
        end: ZonedDateTime
    ): List<FinanceOperation> {

        val startDate = start.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
        val endDate = end.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)

        return financesDao
            .getFinanceOperationsInRange(startDate, endDate)
            .map(FinanceOperationEntity::toDomainModel)
    }

    override fun calculateProfitForCurrency(
        start: ZonedDateTime,
        end: ZonedDateTime,
        currency: Currency
    ): Flow<Money> {

        val startDate = start.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
        val endDate = end.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)

        return financesDao
            .calculateProfitForCurrency(startDate, endDate, currency.name)
            .map { profitInCents ->
                Money(profitInCents, currency)
            }
    }
}