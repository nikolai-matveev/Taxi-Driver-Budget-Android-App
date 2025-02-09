package ru.claus42.taxidriverbudget.feature.goal.edit.goal.viewmodel

import ru.claus42.taxidriverbudget.domain.model.Currency
import java.time.ZonedDateTime

sealed class EditGoalIntent {
    data class SelectStartDate(val date: ZonedDateTime): EditGoalIntent()
    data class SelectCompletionDate(val date: ZonedDateTime): EditGoalIntent()
    data class SetOperationValue(
        val amountForPeriod: Long,
        val currency: Currency,
    ): EditGoalIntent()
    data object AddGoalClicked: EditGoalIntent()
}