package ru.claus42.taxidriverbudget.feature.finance.view.model

import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceOperation
import ru.claus42.taxidriverbudget.feature.finance.model.PeriodType
import java.time.ZonedDateTime

private const val DAYS_IN_WEEK = 7L

data class FinanceScreenState(
    val periodType: PeriodType,
    val dateInterval: Pair<ZonedDateTime, ZonedDateTime>,
    val operations: List<FinanceOperation>,
    val currency: Currency = Currency.RUB,
    val isLoading: Boolean = false,
)