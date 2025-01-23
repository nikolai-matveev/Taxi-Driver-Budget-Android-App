package ru.claus42.taxidriverbudget.feature.finance.model

import ru.claus42.taxidriverbudget.domain.model.Currency
import java.time.ZonedDateTime

data class ChartInfo(
    val value: Int,
    val currency: Currency,
    val date: ZonedDateTime,
    val periodType: PeriodType,
)