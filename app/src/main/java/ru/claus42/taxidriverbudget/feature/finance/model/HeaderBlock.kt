package ru.claus42.taxidriverbudget.feature.finance.model

import ru.claus42.taxidriverbudget.domain.model.Currency
import java.time.ZonedDateTime

data class HeaderBlock(
    val periodType: PeriodType,
    val fullIncome: Int,
    val currency: Currency,
    val dateInterval: Pair<ZonedDateTime, ZonedDateTime>,
)