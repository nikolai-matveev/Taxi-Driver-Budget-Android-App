package ru.claus42.taxidriverbudget.feature.finance.screen.main.model

import ru.claus42.taxidriverbudget.domain.model.Money
import java.time.ZonedDateTime

data class HeaderBlock(
    val periodType: PeriodType,
    val fullIncome: Money,
    val dateInterval: Pair<ZonedDateTime, ZonedDateTime>,
)