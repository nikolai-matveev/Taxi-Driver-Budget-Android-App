package ru.claus42.taxidriverbudget.feature.finance.screen.add.operation.viewmodel

import ru.claus42.taxidriverbudget.domain.model.CategoryType
import ru.claus42.taxidriverbudget.domain.model.FinanceFlowType
import java.time.ZonedDateTime

sealed class AddOperationIntent {
    data class SelectDate(val date: ZonedDateTime): AddOperationIntent()
    data class SelectFinancialFlowType(val financeFlowType: FinanceFlowType): AddOperationIntent()
    data class SetOperationValue(
        val flowType: FinanceFlowType,
        val categoryType: CategoryType,
        val value: Long,
    ): AddOperationIntent()
    data object AddOperationClicked: AddOperationIntent()
}