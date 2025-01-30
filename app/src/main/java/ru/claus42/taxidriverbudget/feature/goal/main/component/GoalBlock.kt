package ru.claus42.taxidriverbudget.feature.goal.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.claus42.taxidriverbudget.R
import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.Money
import ru.claus42.taxidriverbudget.ui.modifier.center
import ru.claus42.taxidriverbudget.ui.theme.TaxiDriverBudgetTheme
import ru.claus42.taxidriverbudget.ui.theme.Yellow
import ru.claus42.taxidriverbudget.utils.toLocalizedDayAndMonthNumeric

import java.time.ZonedDateTime

@Composable
fun GoalBlock(
    progressMoney: Money,
    goalMoney: Money,
    startDate: ZonedDateTime,
    endDate: ZonedDateTime,
    onClick: () -> Unit
) {
    val start = startDate.toLocalizedDayAndMonthNumeric()
    val end = endDate.toLocalizedDayAndMonthNumeric()
    val progressFraction = progressMoney.amountInCents.toFloat() / goalMoney.amountInCents

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,

    ) {
        Text(
            text = stringResource(R.string.goal_from_to).format(start, end),
            modifier = Modifier.align(Alignment.TopCenter)
        )

        CircularProgressIndicator(
            progress = { progressFraction },
            modifier = Modifier.size(115.dp).offset(y = 10.dp),
            strokeWidth = 12.dp,
            color = Yellow
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = progressMoney.toString(), fontSize = 16.sp, fontWeight = Bold)
            Text(text = goalMoney.toString(), fontSize = 16.sp, fontWeight = Bold)
        }
    }
}

@Preview
@Composable
fun PreviewGoalBlock() {
    TaxiDriverBudgetTheme {
        GoalBlock(
            progressMoney = Money(600000, Currency.RUB),
            goalMoney = Money(1000000, Currency.RUB),
            startDate = ZonedDateTime.now(),
            endDate = ZonedDateTime.now().plusDays(7),
            onClick = {}
        )
    }
}