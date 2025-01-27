package ru.claus42.taxidriverbudget.domain.model

import java.time.ZonedDateTime
import java.util.UUID

data class FinanceOperation(
    val id: UUID,
    val money: Money,
    val flowType: FinanceFlowType,
    val categoryType: CategoryType,
    val date: ZonedDateTime,
)


