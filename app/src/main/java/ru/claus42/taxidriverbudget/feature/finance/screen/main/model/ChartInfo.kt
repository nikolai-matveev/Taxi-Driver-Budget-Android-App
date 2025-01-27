package ru.claus42.taxidriverbudget.feature.finance.screen.main.model

import ru.claus42.taxidriverbudget.domain.model.Money
import java.time.ZonedDateTime

data class ChartInfo(
    val money: Money,
    val date: ZonedDateTime,
    val periodType: PeriodType,
)