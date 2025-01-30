package ru.claus42.taxidriverbudget.domain.model

import java.time.ZonedDateTime

data class Goal(
    val id: Int,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime,
    val expectedProfit: Money,
)
