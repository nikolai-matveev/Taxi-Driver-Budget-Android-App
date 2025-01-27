package ru.claus42.taxidriverbudget.feature.finance.screen.main.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.claus42.taxidriverbudget.R
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.PeriodType
import ru.claus42.taxidriverbudget.ui.theme.SelectedTabBgColor
import ru.claus42.taxidriverbudget.ui.theme.UnelectedTabBgColor

@Composable
fun PeriodTypeTabs(
    modifier: Modifier = Modifier,
    periodType: PeriodType,
    onPeriodSelected: (PeriodType) -> Unit
) {
    var selectedPeriodType by remember { mutableStateOf(periodType) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PeriodTabItem(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.week),
            isSelected = selectedPeriodType == PeriodType.WEEK
        ) {
            if (selectedPeriodType != PeriodType.WEEK) {
                selectedPeriodType = PeriodType.WEEK
                onPeriodSelected(PeriodType.WEEK)
            }
        }

        PeriodTabItem(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.month),
            isSelected = selectedPeriodType == PeriodType.MONTH
        ) {
            if (selectedPeriodType != PeriodType.MONTH) {
                selectedPeriodType = PeriodType.MONTH
                onPeriodSelected(PeriodType.MONTH)
            }
        }
    }
}

@Composable
private fun PeriodTabItem(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) SelectedTabBgColor else UnelectedTabBgColor
    )
    val titleColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else Color.Black
    )

    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp,
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp,
                )
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, color = titleColor)
    }
}