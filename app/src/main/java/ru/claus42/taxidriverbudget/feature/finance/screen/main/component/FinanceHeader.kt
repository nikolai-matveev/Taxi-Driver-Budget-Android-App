package ru.claus42.taxidriverbudget.feature.finance.screen.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.claus42.taxidriverbudget.R
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.HeaderBlock
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.PeriodType
import ru.claus42.taxidriverbudget.ui.theme.Gray
import ru.claus42.taxidriverbudget.utils.toLocalizedShort

@Composable
fun FinanceHeader(
    headerBlock: HeaderBlock,
    onPeriodBack: () -> Unit,
    onPeriodForward: () -> Unit,
) {
    val totalValueTitle = headerBlock.fullIncome.toString()
    val subtitleRes = when (headerBlock.periodType) {
        PeriodType.WEEK -> R.string.income_per_week
        PeriodType.MONTH -> R.string.income_per_year
    }
    val subtitle = stringResource(subtitleRes)
    val timePeriod = with(headerBlock) {
        val year = dateInterval.first.year.toString()

        when (headerBlock.periodType) {
            PeriodType.WEEK -> {
                val startDate = dateInterval.first.toLocalizedShort()
                val endDate = dateInterval.second.toLocalizedShort()
                "$startDate\n$endDate"
            }

            PeriodType.MONTH -> year
        }
    }
    val timePeriodType = when (headerBlock.periodType) {
        PeriodType.WEEK -> MaterialTheme.typography.bodyLarge
        PeriodType.MONTH -> MaterialTheme.typography.titleLarge
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = totalValueTitle,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = Gray,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onPeriodBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = timePeriod,
                textAlign = TextAlign.Center,
                style = timePeriodType
            )
            IconButton(onClick = onPeriodForward) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Back"
                )
            }
        }
    }
}