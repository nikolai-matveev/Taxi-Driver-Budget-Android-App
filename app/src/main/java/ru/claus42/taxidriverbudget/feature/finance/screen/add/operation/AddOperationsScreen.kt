package ru.claus42.taxidriverbudget.feature.finance.screen.add.operation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.claus42.taxidriverbudget.R
import ru.claus42.taxidriverbudget.domain.model.CategoryType
import ru.claus42.taxidriverbudget.domain.model.FinanceFlowType
import ru.claus42.taxidriverbudget.ui.component.SelectDate
import ru.claus42.taxidriverbudget.ui.component.MoneyInputField
import ru.claus42.taxidriverbudget.feature.finance.screen.add.operation.viewmodel.AddOperationIntent
import ru.claus42.taxidriverbudget.feature.finance.screen.add.operation.viewmodel.AddOperationSideEffect
import ru.claus42.taxidriverbudget.feature.finance.screen.add.operation.viewmodel.AddOperationViewModel
import ru.claus42.taxidriverbudget.ui.component.BigGreenButton
import ru.claus42.taxidriverbudget.ui.theme.TaxiDriverBudgetTheme

@Composable
fun AddOperationsScreen(
    viewModel: AddOperationViewModel = hiltViewModel(),
    navBackToFinanceScreen: () -> Unit,
) {
    val scrollState = rememberScrollState()

    val state by viewModel.container.stateFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 20.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center
        ) {
            SelectDate(
                label = stringResource(R.string.select_date),
                selectedDate = state.selectedDate
            ) {
                viewModel.handleIntent(
                    AddOperationIntent.SelectDate(it)
                )
            }

            Column(
                Modifier.padding(vertical = 15.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.financeFlowType == FinanceFlowType.INCOME,
                        onClick = {
                            viewModel.handleIntent(
                                AddOperationIntent.SelectFinancialFlowType(FinanceFlowType.INCOME)
                            )
                        },
                        colors = RadioButtonDefaults.colors().copy(
                            selectedColor = Color.Red,
                            unselectedColor = Color.Gray,
                        ),
                    )
                    Text(
                        text = stringResource(R.string.income)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.financeFlowType == FinanceFlowType.EXPENSE,
                        onClick = {
                            viewModel.handleIntent(
                                AddOperationIntent.SelectFinancialFlowType(FinanceFlowType.EXPENSE)
                            )
                        },
                        colors = RadioButtonDefaults.colors().copy(
                            selectedColor = Color.Red,
                            unselectedColor = Color.Gray,
                        ),
                    )
                    Text(
                        text = stringResource(R.string.expense)
                    )
                }
            }

            val incomeFields = remember(state) {
                listOf(
                    Triple(R.string.cash, CategoryType.CASH, derivedStateOf { state.cashAmount }),
                    Triple(
                        R.string.non_cash,
                        CategoryType.NON_CASH,
                        derivedStateOf { state.nonCashAmount }),
                    Triple(
                        R.string.bank_transfer,
                        CategoryType.TRANSFER,
                        derivedStateOf { state.bankTransferAmount }),
                    Triple(R.string.tips, CategoryType.TIPS, derivedStateOf { state.tipsAmount }),
                )
            }


            val expenseFields = remember(state) {
                listOf(
                    Triple(R.string.fuel, CategoryType.FUEL, derivedStateOf { state.fuelAmount }),
                    Triple(R.string.food, CategoryType.FOOD, derivedStateOf { state.foodAmount }),
                    Triple(
                        R.string.mileage,
                        CategoryType.MILEAGE,
                        derivedStateOf { state.mileageAmount }),
                    Triple(
                        R.string.other,
                        CategoryType.OTHER,
                        derivedStateOf { state.otherAmount }),
                )
            }

            when (state.financeFlowType) {
                FinanceFlowType.INCOME -> {
                    incomeFields.forEach { (labelStringRes, categoryType, amountState) ->
                        MoneyInputField(
                            label = stringResource(labelStringRes),
                            currency = state.currency,
                            financeFlowType = FinanceFlowType.INCOME,
                            amount = amountState.value,
                            onValueChanged = { amountInCents ->
                                viewModel.handleIntent(
                                    AddOperationIntent.SetOperationValue(
                                        FinanceFlowType.INCOME,
                                        categoryType,
                                        amountInCents
                                    )
                                )
                            }
                        )
                    }
                }

                FinanceFlowType.EXPENSE -> {
                    expenseFields.forEach { (labelStringRes, categoryType, amountState) ->
                        MoneyInputField(
                            label = stringResource(labelStringRes),
                            currency = state.currency,
                            financeFlowType = FinanceFlowType.EXPENSE,
                            amount = amountState.value,
                            onValueChanged = { amountInCents ->
                                viewModel.handleIntent(
                                    AddOperationIntent.SetOperationValue(
                                        FinanceFlowType.EXPENSE,
                                        categoryType,
                                        amountInCents
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }

        BigGreenButton(
            text = stringResource(R.string.save),
            modifier = Modifier.padding(bottom = 22.dp),
        ) {
            viewModel.handleIntent(AddOperationIntent.AddOperationClicked)
        }
    }

    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                AddOperationSideEffect.AddNewOperation -> navBackToFinanceScreen()
            }
        }
    }
}


@Preview
@Composable
fun PreviewAddOperationScreen() {
    TaxiDriverBudgetTheme {
        AddOperationsScreen {}
    }
}