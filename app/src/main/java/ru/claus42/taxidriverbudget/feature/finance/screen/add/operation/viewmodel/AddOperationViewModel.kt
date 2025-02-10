package ru.claus42.taxidriverbudget.feature.finance.screen.add.operation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.claus42.taxidriverbudget.domain.model.CategoryType
import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceFlowType
import ru.claus42.taxidriverbudget.domain.model.FinanceOperation
import ru.claus42.taxidriverbudget.domain.model.Money
import ru.claus42.taxidriverbudget.domain.repository.FinanceRepository
import java.time.ZonedDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddOperationViewModel @Inject constructor(
    private val repository: FinanceRepository,
) : ContainerHost<AddOperationScreenState, AddOperationSideEffect>, ViewModel() {
    override val container = container<AddOperationScreenState, AddOperationSideEffect>(
        initialState = AddOperationScreenState(
            selectedDate = ZonedDateTime.now(),
            financeFlowType = FinanceFlowType.INCOME,
            currency = Currency.RUB,
            cashAmount = 0,
            nonCashAmount = 0,
            bankTransferAmount = 0,
            tipsAmount = 0,
            foodAmount = 0,
            fuelAmount = 0,
            mileageAmount = 0,
            otherAmount = 0,
            operationMap = emptyMap(),
        )
    )

    fun handleIntent(intent: AddOperationIntent) = intent {
        when (intent) {
            AddOperationIntent.AddOperationClicked -> {
                try {
                    saveOperations(state)
                    postSideEffect(AddOperationSideEffect.AddNewOperation)
                } catch (e: Exception) {
                    //todo
                }
            }
            is AddOperationIntent.SelectDate -> {
                reduce { state.copy(selectedDate = intent.date) }
            }
            is AddOperationIntent.SelectFinancialFlowType -> {
                reduce { state.copy(financeFlowType = intent.financeFlowType) }
            }
            is AddOperationIntent.SetOperationValue -> {
                reduce {
                    val updateState = when (intent.categoryType) {
                        CategoryType.CASH -> state.copy(cashAmount = intent.value)
                        CategoryType.NON_CASH -> state.copy(nonCashAmount = intent.value)
                        CategoryType.TRANSFER -> state.copy(bankTransferAmount = intent.value)
                        CategoryType.TIPS -> state.copy(tipsAmount = intent.value)
                        CategoryType.FUEL -> state.copy(fuelAmount = intent.value)
                        CategoryType.FOOD -> state.copy(foodAmount = intent.value)
                        CategoryType.OTHER -> state.copy(otherAmount = intent.value)
                        CategoryType.MILEAGE -> state.copy(mileageAmount = intent.value)
                    }
                    updateState.copy(
                        operationMap = state.operationMap
                                + (Pair(intent.flowType, intent.categoryType) to intent.value)
                    )
                }
            }
        }
    }


    private suspend fun saveOperations(state: AddOperationScreenState) {
        state.operationMap.filter { it.value != 0L }.forEach { (key, amountInCents) ->
            val financeFlowType = key.first
            val categoryType = key.second

            repository.add(
                FinanceOperation(
                    id = UUID.randomUUID(),
                    money = Money(
                        amountInCents = amountInCents,
                        currency = state.currency
                    ),
                    flowType = financeFlowType,
                    categoryType = categoryType,
                    date = state.selectedDate
                )
            )
        }
    }

}