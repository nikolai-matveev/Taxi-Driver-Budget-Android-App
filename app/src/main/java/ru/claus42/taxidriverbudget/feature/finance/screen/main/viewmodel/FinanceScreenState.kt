package ru.claus42.taxidriverbudget.feature.finance.screen.main.viewmodel

import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceOperation
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.PeriodType
import java.time.ZonedDateTime

data class FinanceScreenState(
    val periodType: PeriodType,
    val dateInterval: Pair<ZonedDateTime, ZonedDateTime>,
    val operations: List<FinanceOperation>,
    val currency: Currency = Currency.RUB,
    val isLoading: Boolean = false,
)