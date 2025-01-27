package ru.claus42.taxidriverbudget.feature.finance.screen.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.claus42.taxidriverbudget.R
import ru.claus42.taxidriverbudget.domain.model.CategoryType
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.OperationBlock
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.OperationsWithDate
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.PeriodType
import ru.claus42.taxidriverbudget.ui.modifier.bottomFadingEdge
import ru.claus42.taxidriverbudget.ui.modifier.topFadingEdge
import ru.claus42.taxidriverbudget.ui.theme.Gray
import ru.claus42.taxidriverbudget.utils.toLocalizedDayAndMonthName
import ru.claus42.taxidriverbudget.utils.toLocalizedMonthName

@Composable
fun FinanceOperations(
    modifier: Modifier = Modifier,
    operationBlock: OperationBlock,
    lazyListState: LazyListState = rememberLazyListState(),
    onDateClick: (Int) -> Unit
) {
    val fadingColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)

    if (operationBlock.operationsWithDateList.isNotEmpty()) {
        LazyColumn(
            state = lazyListState,
            modifier = modifier
                .fillMaxWidth()
                .topFadingEdge(
                    color = fadingColor,
                    isVisible = lazyListState.canScrollBackward,
                )
                .bottomFadingEdge(
                    color = fadingColor,
                    isVisible = lazyListState.canScrollForward,
                )

        ) {
            itemsIndexed(operationBlock.operationsWithDateList) { index, operationsWithDate ->
                FinanceOperationBlock(
                    modifier = modifier,
                    periodType = operationBlock.periodType,
                    operationsWithDate = operationsWithDate,
                ) {
                    onDateClick(index)
                }
            }
        }
    } else {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.no_operations),
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun FinanceOperationBlock(
    modifier: Modifier = Modifier,
    periodType: PeriodType,
    operationsWithDate: OperationsWithDate,
    onClick: () -> Unit,
) {
    val (date, operations) = operationsWithDate

    val dateText = when (periodType) {
        PeriodType.WEEK -> date.toLocalizedDayAndMonthName()
        PeriodType.MONTH -> date.toLocalizedMonthName()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .clickable { onClick() },
            text = dateText,
            style = MaterialTheme.typography.bodyLarge.copy(color = Gray)
        )

        operations.forEach { operation ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 11.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val categoryText = stringResource(operation.categoryType.toStringResId())
                val valueText = operation.money.toString()

                CircularCheckbox(
                    uuid = operation.uuid,
                ) { uuid, isChecked ->
                    //todo
                }
                Text(
                    text = categoryText,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f)
                )
                Text(
                    text = valueText
                )
            }
        }
    }

}

private fun CategoryType.toStringResId() = when (this) {
    CategoryType.CASH -> R.string.cash
    CategoryType.NON_CASH -> R.string.non_cash
    CategoryType.TRANSFER -> R.string.bank_transfer
    CategoryType.TIPS -> R.string.tips
    CategoryType.FUEL -> R.string.fuel
    CategoryType.FOOD -> R.string.food
    CategoryType.OTHER -> R.string.other
    CategoryType.MILEAGE -> R.string.mileage
}