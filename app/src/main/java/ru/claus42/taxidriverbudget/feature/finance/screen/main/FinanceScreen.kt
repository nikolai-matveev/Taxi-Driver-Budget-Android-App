package ru.claus42.taxidriverbudget.feature.finance.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
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
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.mapper.toAggregatedFinanceState
import ru.claus42.taxidriverbudget.feature.finance.screen.main.component.ChartRow
import ru.claus42.taxidriverbudget.feature.finance.screen.main.component.FinanceHeader
import ru.claus42.taxidriverbudget.feature.finance.screen.main.component.FinanceOperations
import ru.claus42.taxidriverbudget.feature.finance.screen.main.component.PeriodTypeTabs
import ru.claus42.taxidriverbudget.feature.finance.screen.main.viewmodel.FinanceIntent
import ru.claus42.taxidriverbudget.feature.finance.screen.main.viewmodel.FinanceSideEffect
import ru.claus42.taxidriverbudget.feature.finance.screen.main.viewmodel.FinanceViewModel
import ru.claus42.taxidriverbudget.ui.component.BigGreenButton
import ru.claus42.taxidriverbudget.ui.theme.TaxiDriverBudgetTheme

@Composable
fun FinanceScreen(
    viewModel: FinanceViewModel = hiltViewModel(),
    navigateAddOperationsScreen: () -> Unit,
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val aggregatedState by remember(state) {
        derivedStateOf { state.toAggregatedFinanceState() }
    }

    val chartListState = rememberLazyListState()
    val financeOperationsListState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                vertical = VERTICAL_SCREEN_PADDING,
                horizontal = HORIZONTAL_SCREEN_PADDING,
            ),
    ) {
        FinanceHeader(
            headerBlock = aggregatedState.headerBlock,
            onPeriodBack = {
                viewModel.handleIntent(FinanceIntent.BackDate)
            },
            onPeriodForward = {
                viewModel.handleIntent(FinanceIntent.ForwardDate)
            }
        )

        ChartRow(
            modifier = Modifier.padding(
                top = 23.dp
            ),
            chartInfos = aggregatedState.chartInfos,
            lazyListState = chartListState,
        ) {
            viewModel.handleIntent(FinanceIntent.IndexedChartClicked(it))
        }

        PeriodTypeTabs(
            modifier = Modifier.padding(top = 8.dp, bottom = 22.dp),
            periodType = aggregatedState.periodType
        ) {
            viewModel.handleIntent(FinanceIntent.SwitchPeriodTypeTab(it))
        }

        FinanceOperations(
            modifier = Modifier.weight(1f),
            lazyListState = financeOperationsListState,
            operationBlock = aggregatedState.operationBlock
        ) {
            viewModel.handleIntent(FinanceIntent.DateWithIndexClicked(it))
        }

        BigGreenButton(
            text = stringResource(R.string.add_new_operation),
            modifier = Modifier.padding(top = 22.dp),
        ) {
            viewModel.handleIntent(FinanceIntent.AddOperationClicked)
        }
    }

    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                FinanceSideEffect.NavigateToAddOperationScreen -> {
                    navigateAddOperationsScreen()
                }

                is FinanceSideEffect.ScrollChartToPosition -> {
                    chartListState.animateScrollToItem(sideEffect.index)
                }

                is FinanceSideEffect.ScrollOperationListToPosition -> {
                    financeOperationsListState.animateScrollToItem(sideEffect.index)
                }

                is FinanceSideEffect.ShowError -> {
                    //TODO
                }
            }
        }
    }
}

private val VERTICAL_SCREEN_PADDING = 22.dp
private val HORIZONTAL_SCREEN_PADDING = 20.dp


@Preview
@Composable
fun PreviewFinance() {
    TaxiDriverBudgetTheme {
        FinanceScreen() {}
    }
}
