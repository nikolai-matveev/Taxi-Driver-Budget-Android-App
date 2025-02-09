package ru.claus42.taxidriverbudget.domain.model

import java.time.ZonedDateTime

data class Goal(
    val id: Int = 0,
    val startDate: ZonedDateTime,
    val completionDate: ZonedDateTime,
    val expectedProfit: Money,
)
