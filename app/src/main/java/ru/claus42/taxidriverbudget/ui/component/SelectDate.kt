package ru.claus42.taxidriverbudget.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.claus42.taxidriverbudget.R
import ru.claus42.taxidriverbudget.ui.drawables.icon.CalendarIco
import ru.claus42.taxidriverbudget.ui.theme.DarkGray
import ru.claus42.taxidriverbudget.ui.theme.FireBrick
import ru.claus42.taxidriverbudget.ui.theme.LightGray
import ru.claus42.taxidriverbudget.ui.theme.PurpleGrey80
import ru.claus42.taxidriverbudget.utils.toLocalizedDayMonthYear
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@Composable
fun SelectDate(
    modifier: Modifier = Modifier,
    label: String,
    selectedDate: ZonedDateTime,
    onDateSelected: (ZonedDateTime) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    color = DarkGray,
                )
                Text(
                    text = selectedDate.toLocalizedDayMonthYear(),
                    style = dateStyle
                )
            }

            IconButton(
                onClick = { showDialog = true }
            ) {
                Icon(
                    imageVector = CalendarIco,
                    contentDescription = "Select date",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }

            if (showDialog) {
                DatePickerModal(
                    onDateSelected = { millis ->
                        millis?.let {
                            val date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
                            onDateSelected(date)
                        }
                    },
                    onDismiss = { showDialog = false }
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp),
            thickness = 1.dp,
            color = LightGray
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        colors = DatePickerDefaults.colors().copy(
            containerColor = Color.White
        ),
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text(
                    text = stringResource(R.string.ok),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors().copy(
                containerColor = Color.White,
                selectedDayContentColor = FireBrick,
                selectedDayContainerColor = PurpleGrey80,
                todayDateBorderColor = MaterialTheme.colorScheme.onSurface,
                todayContentColor = MaterialTheme.colorScheme.onSurface,
            )
        )
    }
}


private val dateStyle = TextStyle(
    fontFamily = FontFamily(Font(R.font.abel_regular)),
    fontWeight = FontWeight.Normal,
    fontSize = 17.sp,
    lineHeight = 24.sp,
    color = Color(0xFF1D2023)
)