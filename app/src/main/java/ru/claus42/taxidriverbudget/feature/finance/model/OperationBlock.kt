package ru.claus42.taxidriverbudget.feature.finance.model

import java.time.ZonedDateTime

typealias OperationsWithDate = Pair<ZonedDateTime, List<Operation>>

data class OperationBlock(
    val periodType: PeriodType,
    val operationsWithDateList: List<OperationsWithDate>,
)