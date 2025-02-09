package ru.claus42.taxidriverbudget.feature.goal.edit.goal.viewmodel

import ru.claus42.taxidriverbudget.domain.model.Currency
import java.time.ZonedDateTime

data class EditGoalScreenState(
    val startDate: ZonedDateTime,
    val completionDate: ZonedDateTime,
    val amountForPeriod: Long,
    val currency: Currency,
)