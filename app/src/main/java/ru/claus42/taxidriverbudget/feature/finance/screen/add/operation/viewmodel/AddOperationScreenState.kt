package ru.claus42.taxidriverbudget.feature.finance.screen.add.operation.viewmodel

import ru.claus42.taxidriverbudget.domain.model.CategoryType
import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceFlowType
import java.time.ZonedDateTime

data class AddOperationScreenState(
    val selectedDate: ZonedDateTime,
    val financeFlowType: FinanceFlowType,
    val currency: Currency,
    val operationMap: Map<Pair<FinanceFlowType, CategoryType>, Long>,
)