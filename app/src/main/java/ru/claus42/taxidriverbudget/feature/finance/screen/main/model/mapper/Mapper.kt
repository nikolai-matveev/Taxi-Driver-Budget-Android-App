package ru.claus42.taxidriverbudget.feature.finance.screen.main.model.mapper

import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceOperation
import ru.claus42.taxidriverbudget.domain.model.Money
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.AggregatedFinanceState
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.ChartInfo
import ru.claus42.taxidriverbudget.feature.finance.screen.main.viewmodel.FinanceScreenState
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.HeaderBlock
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.OperationsWithDate
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.Operation
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.OperationBlock
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.PeriodType
import ru.claus42.taxidriverbudget.utils.getMonthAndYear
import ru.claus42.taxidriverbudget.utils.toStartOfDay
import java.time.ZonedDateTime

fun FinanceScreenState.toAggregatedFinanceState(): AggregatedFinanceState {
    return AggregatedFinanceState(
        headerBlock = HeaderBlock(
            periodType = periodType,
            fullIncome = Money(
                amountInCents = operations.sumOf { it.money.amountInCents },
                currency = currency,
            ),
            dateInterval = dateInterval
        ),
        periodType = periodType,
        chartInfos = operations.toChartInfos(periodType, currency),
        operationBlock = OperationBlock(
            periodType = periodType,
            operationsWithDateList = operations.toOperationsGroupedByDate(periodType)
        )
    )
}

fun List<FinanceOperation>.toChartInfos(
    periodType: PeriodType,
    currency: Currency,
): List<ChartInfo> = when (periodType) {
    PeriodType.WEEK -> toDayChartInfos(periodType, currency)
    PeriodType.MONTH -> toMonthChartInfos(periodType, currency)
}

fun List<FinanceOperation>.toDayChartInfos(
    periodType: PeriodType,
    currency: Currency,
): List<ChartInfo> {
    return groupBy { it.date.toStartOfDay() }.toSortedMap().values.map { operationsByMonth ->
        val totalValue = operationsByMonth.sumOf { it.money.amountInCents }
        val date = operationsByMonth[0].date.toStartOfDay()

        ChartInfo(
            money = Money(
                amountInCents = totalValue,
                currency = currency,
            ),
            date = date,
            periodType = periodType,
        )
    }
}

fun List<FinanceOperation>.toMonthChartInfos(
    periodType: PeriodType,
    currency: Currency,
): List<ChartInfo> {
    return groupBy { it.date.month }.toSortedMap().values.map { operationsByMonth ->
        val totalValue = operationsByMonth.sumOf { it.money.amountInCents }
        val date = with(operationsByMonth[0].date) {
            withDayOfMonth(1).toLocalDate().atStartOfDay(this.zone)
        }

        ChartInfo(
            money = Money(
                amountInCents = totalValue,
                currency = currency,
            ),
            date = date,
            periodType = periodType,
        )
    }
}

fun List<FinanceOperation>.toOperationsGroupedByDate(
    periodType: PeriodType
): List<OperationsWithDate> = when (periodType) {
    PeriodType.WEEK -> groupOperationsBy { it.toStartOfDay() }
    PeriodType.MONTH -> groupOperationsBy { it.getMonthAndYear() }
}

private fun List<FinanceOperation>.groupOperationsBy(
    keySelector: (ZonedDateTime) -> ZonedDateTime
): List<OperationsWithDate> {
    return groupBy { keySelector(it.date) }
        .map { (date, operations) -> Pair(date, operations.map(FinanceOperation::toOperation)) }
}

fun FinanceOperation.toOperation(): Operation =
    Operation(
        uuid = id,
        categoryType = categoryType,
        money = money,
    )