package ru.claus42.taxidriverbudget.feature.finance.view.model

import ru.claus42.taxidriverbudget.feature.finance.model.PeriodType

sealed class FinanceIntent {
    data object BackDate: FinanceIntent()
    data object ForwardDate: FinanceIntent()
    data class SwitchPeriodTypeTab(val periodType: PeriodType): FinanceIntent()
    data class IndexedChartClicked(val index: Int): FinanceIntent()
    data class DateWithIndexClicked(val index: Int): FinanceIntent()
    data object AddOperationClicked: FinanceIntent()
}