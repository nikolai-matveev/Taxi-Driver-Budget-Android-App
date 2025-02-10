package ru.claus42.taxidriverbudget.feature.finance.screen.add.operation.viewmodel

import ru.claus42.taxidriverbudget.domain.model.CategoryType
import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceFlowType
import java.time.ZonedDateTime

data class AddOperationScreenState(
    val selectedDate: ZonedDateTime,
    val financeFlowType: FinanceFlowType,
    val currency: Currency,
    val cashAmount: Long,
    val nonCashAmount: Long,
    val bankTransferAmount: Long,
    val tipsAmount: Long,
    val foodAmount: Long,
    val fuelAmount: Long,
    val mileageAmount: Long,
    val otherAmount: Long,
    val operationMap: Map<Pair<FinanceFlowType, CategoryType>, Long>,
)