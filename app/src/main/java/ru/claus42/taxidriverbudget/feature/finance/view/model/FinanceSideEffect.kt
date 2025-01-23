package ru.claus42.taxidriverbudget.feature.finance.view.model

sealed class FinanceSideEffect {
    data object NavigateToAddOperationScreen : FinanceSideEffect()
    data class ScrollOperationListToPosition(val index: Int): FinanceSideEffect()
    data class ScrollChartToPosition(val index: Int): FinanceSideEffect()
    data class ShowError(val errorMessage: String): FinanceSideEffect()
}