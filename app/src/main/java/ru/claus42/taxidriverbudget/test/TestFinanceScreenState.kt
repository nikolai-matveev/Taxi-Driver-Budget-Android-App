package ru.claus42.taxidriverbudget.test

import ru.claus42.taxidriverbudget.domain.model.CategoryType
import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceFlowType
import ru.claus42.taxidriverbudget.domain.model.FinanceOperation
import ru.claus42.taxidriverbudget.feature.finance.view.model.FinanceScreenState
import ru.claus42.taxidriverbudget.feature.finance.model.PeriodType
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID
import kotlin.random.Random

fun getTestFinanceScreenState(): FinanceScreenState {
    val startDate = ZonedDateTime.of(2024, 8, 6, 0, 0, 0, 0, ZoneId.systemDefault())
    val endDate = ZonedDateTime.of(2025, 2, 4, 0, 0, 0, 0, ZoneId.systemDefault())

    return FinanceScreenState(
        periodType = PeriodType.WEEK,
        dateInterval = Pair(startDate, endDate),
        operations = generateTestFinanceOperations(startDate, endDate),
        isLoading = false
    )
}

fun generateTestFinanceOperations(
    startDate: ZonedDateTime,
    endDate: ZonedDateTime,
): List<FinanceOperation> {
    val numEntries = 150
    val operations = mutableListOf<FinanceOperation>()


    repeat(numEntries) {
        // Генерация случайной даты между startDate и endDate
        val randomDays = Random.nextLong(ChronoUnit.DAYS.between(startDate, endDate))
        val date = startDate.plusDays(randomDays)

        val value = Random.nextInt(-8000, 8000)

        val flowType = if (value < 0) FinanceFlowType.EXPENSE else FinanceFlowType.INCOME
        val categoryType = if (flowType == FinanceFlowType.EXPENSE) {
            CategoryType.entries.filter {
                it in listOf(
                    CategoryType.FUEL,
                    CategoryType.FOOD,
                    CategoryType.OTHER,
                    CategoryType.MILEAGE
                )
            }.random()
        } else {
            CategoryType.entries.filter {
                it in listOf(
                    CategoryType.CASH,
                    CategoryType.NON_CASH,
                    CategoryType.TRANSFER,
                    CategoryType.TIPS
                )
            }.random()
        }

        operations.add(
            FinanceOperation(
                id = UUID.randomUUID(),
                value = value,
                currency = Currency.RUB,
                flowType = flowType,
                categoryType = categoryType,
                date = date
            )
        )
    }

    return operations.sortedBy { it.date }
}