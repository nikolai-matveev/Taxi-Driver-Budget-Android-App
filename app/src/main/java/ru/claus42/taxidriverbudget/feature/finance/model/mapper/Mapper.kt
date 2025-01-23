package ru.claus42.taxidriverbudget.feature.finance.model.mapper

import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceOperation
import ru.claus42.taxidriverbudget.feature.finance.model.AggregatedFinanceState
import ru.claus42.taxidriverbudget.feature.finance.model.ChartInfo
import ru.claus42.taxidriverbudget.feature.finance.view.model.FinanceScreenState
import ru.claus42.taxidriverbudget.feature.finance.model.HeaderBlock
import ru.claus42.taxidriverbudget.feature.finance.model.OperationsWithDate
import ru.claus42.taxidriverbudget.feature.finance.model.Operation
import ru.claus42.taxidriverbudget.feature.finance.model.OperationBlock
import ru.claus42.taxidriverbudget.feature.finance.model.PeriodType
import ru.claus42.taxidriverbudget.utils.getMonthAndYear
import ru.claus42.taxidriverbudget.utils.toStartOfDay
import java.time.ZonedDateTime

fun FinanceScreenState.toAggregatedFinanceState(): AggregatedFinanceState {
    return AggregatedFinanceState(
        headerBlock = HeaderBlock(
            periodType = periodType,
            fullIncome = operations.map(FinanceOperation::value).sum(),
            currency = currency,
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
        val totalValue = operationsByMonth.sumOf { it.value }
        val date = operationsByMonth[0].date.toStartOfDay()

        ChartInfo(
            value = totalValue,
            currency = currency,
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
        val totalValue = operationsByMonth.sumOf { it.value }
        val date = with(operationsByMonth[0].date) {
            withDayOfMonth(1).toLocalDate().atStartOfDay(this.zone)
        }

        ChartInfo(
            value = totalValue,
            currency = currency,
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
        value = value,
        currency = currency
    )