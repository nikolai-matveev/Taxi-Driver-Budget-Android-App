package ru.claus42.taxidriverbudget.feature.finance.screen.add.operation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceFlowType
import ru.claus42.taxidriverbudget.ui.theme.FireBrick

@Composable
fun TransactionInputField(
    modifier: Modifier = Modifier,
    label: String,
    financeFlowType: FinanceFlowType,
    currency: Currency,
    onValueChanged: (amountInCents: Long) -> Unit,
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        value = text,
        trailingIcon = { Text(currency.symbol, style = MaterialTheme.typography.bodyLarge) },
        onValueChange = { input ->
            if (currency.showFractionDigits) {
                if (input.matches(Regex("^\\d*\\.?\\d{0,2}$"))) {
                    text = input
                    onValueChanged(convertStringToAmountWithCents(text, financeFlowType))
                }
            } else {
                if (input.all { it.isDigit() }) {
                    text = input
                    onValueChanged(convertStringToAmountWithCents(text, financeFlowType))
                }
            }
        },
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = if (currency.showFractionDigits) KeyboardType.Decimal else KeyboardType.Number
        ),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = Color.Gray,
            focusedLabelColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            focusedBorderColor = Color.Black,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            selectionColors = TextSelectionColors(
                handleColor = FireBrick,
                backgroundColor = Color.Gray.copy(alpha = 0.4f),
            )
        )
    )
}

private fun convertStringToAmountWithCents(
    text: String,
    financeFlowType: FinanceFlowType
): Long {
    val sign = when (financeFlowType) {
        FinanceFlowType.INCOME -> 1
        FinanceFlowType.EXPENSE -> -1
    }

    val value = if (text.isEmpty()) 0L else (text.toDouble() * 100).toLong()

    return sign * value
}