package ru.claus42.taxidriverbudget.feature.finance.screen.main.component

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.ChartInfo
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.PeriodType
import ru.claus42.taxidriverbudget.ui.modifier.leftFadingEdge
import ru.claus42.taxidriverbudget.ui.modifier.rightFadingEdge
import ru.claus42.taxidriverbudget.ui.theme.ExpenseChartColor
import ru.claus42.taxidriverbudget.ui.theme.IncomeChartColor
import ru.claus42.taxidriverbudget.utils.toLocalizedDayAndMonthNumeric
import ru.claus42.taxidriverbudget.utils.toLocalizedMonthName
import kotlin.math.abs

@Composable
fun ChartRow(
    modifier: Modifier = Modifier,
    chartInfos: List<ChartInfo>,
    lazyListState: LazyListState = rememberLazyListState(),
    onClickListener: (index: Int) -> Unit
) {
    val maxValue = abs(chartInfos.maxByOrNull { abs(it.money.amountInCents) }?.money?.amountInCents ?: 0)
    val fadingColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)

    LazyRow(
        state = lazyListState,
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .leftFadingEdge(
                color = fadingColor,
                isVisible = lazyListState.canScrollBackward
            )
            .rightFadingEdge(
                color = fadingColor,
                isVisible = lazyListState.canScrollForward
            ),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        itemsIndexed(chartInfos) { index, info ->
            ChartColumn(
                index = index,
                chartInfo = info,
                maxValue = maxValue,
                onClickListener = onClickListener,
            )
        }
    }


}

@Composable
private fun ChartColumn(
    modifier: Modifier = Modifier,
    index: Int,
    chartInfo: ChartInfo,
    maxValue: Long,
    maxChartHeight: Dp = 70.dp,
    width: Dp = 60.dp,
    incomeColor: Color = IncomeChartColor,
    expenseColor: Color = ExpenseChartColor,
    onClickListener: (index: Int) -> Unit
) {
    val chartFraction = abs(chartInfo.money.amountInCents.toFloat() / maxValue)
    val isIncome = chartInfo.money.amountInCents >= 0

    val targetHeight = chartFraction * maxChartHeight
    val animatedHeight by animateDpAsState(
        targetValue = targetHeight,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )

    Column(
        modifier = modifier
            .width(width)
            .clickable {
                onClickListener(index)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val topLabel = chartInfo.money.toString()
        val bottomLabel = with(chartInfo) {
            when (periodType) {
                PeriodType.WEEK -> date.toLocalizedDayAndMonthNumeric()
                PeriodType.MONTH -> date.toLocalizedMonthName()
            }
        }


        Text(
            text = topLabel,
            style = MaterialTheme.typography.labelSmall,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, bottom = 4.dp)
                .height(animatedHeight)
                .background(
                    color = if (isIncome) incomeColor else expenseColor,
                    shape = RoundedCornerShape(
                        topStart = 3.dp,
                        topEnd = 3.dp
                    )
                )
        )
        Text(
            text = bottomLabel,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}