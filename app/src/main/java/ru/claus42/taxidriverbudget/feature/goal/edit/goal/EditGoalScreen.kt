package ru.claus42.taxidriverbudget.feature.goal.edit.goal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.claus42.taxidriverbudget.R
import ru.claus42.taxidriverbudget.domain.model.FinanceFlowType
import ru.claus42.taxidriverbudget.feature.goal.edit.goal.viewmodel.EditGoalIntent
import ru.claus42.taxidriverbudget.feature.goal.edit.goal.viewmodel.EditGoalSideEffect
import ru.claus42.taxidriverbudget.feature.goal.edit.goal.viewmodel.EditGoalViewModel
import ru.claus42.taxidriverbudget.ui.component.BigGreenButton
import ru.claus42.taxidriverbudget.ui.component.MoneyInputField
import ru.claus42.taxidriverbudget.ui.component.SelectDate
import ru.claus42.taxidriverbudget.ui.theme.TaxiDriverBudgetTheme

@Composable
fun EditGoalScreen(
    id: Int? = null,
    viewModel: EditGoalViewModel = hiltViewModel(
        creationCallback = { factory: EditGoalViewModel.Factory ->
            factory.create(id)
        }
    ),
    navBackToGoals: () -> Unit,
) {
    val scrollState = rememberScrollState()

    val state by viewModel.container.stateFlow.collectAsState()
    val amountForPeriod by remember(state) {
        derivedStateOf { state.amountForPeriod }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 20.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .offset(y = (-50).dp),
            verticalArrangement = Arrangement.Center
        ) {
            SelectDate(
                modifier = Modifier.padding(bottom = 20.dp),
                label = stringResource(R.string.start_date),
                selectedDate = state.startDate
            ) {
                viewModel.handleIntent(
                    EditGoalIntent.SelectStartDate(it)
                )
            }

            SelectDate(
                modifier = Modifier.padding(bottom = 32.dp),
                label = stringResource(R.string.completion_date),
                selectedDate = state.completionDate
            ) {
                viewModel.handleIntent(
                    EditGoalIntent.SelectCompletionDate(it)
                )
            }

            MoneyInputField(
                label = stringResource(R.string.amount_for_period),
                currency = state.currency,
                financeFlowType = FinanceFlowType.INCOME,
                amount = state.amountForPeriod,
                onValueChanged = { amountInCents ->
                    viewModel.handleIntent(
                        EditGoalIntent.SetOperationValue(amountInCents, state.currency)
                    )
                }
            )
        }

        BigGreenButton(
            text = stringResource(R.string.save),
            modifier = Modifier.padding(bottom = 22.dp),
        ) {
            viewModel.handleIntent(EditGoalIntent.AddGoalClicked)
        }
    }

    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                EditGoalSideEffect.AddNewGoal -> navBackToGoals()
            }
        }
    }
}


@Preview
@Composable
fun PreviewAddOperationScreen() {
    TaxiDriverBudgetTheme {
        EditGoalScreen { }
    }
}