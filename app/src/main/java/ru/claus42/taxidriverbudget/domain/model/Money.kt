package ru.claus42.taxidriverbudget.domain.model

import java.text.DecimalFormat

private const val DEFAULT_FRACTION_DIGITS = 2

data class Money(
    val amountInCents: Long,
    val currency: Currency,
) {
    override fun toString(): String {
        val amount = amountInCents / 100.0

        val pattern = if (currency.showFractionDigits) "#,##0.${"0".repeat(DEFAULT_FRACTION_DIGITS)}" else "#,##0"

        val decimalFormatSymbols = DecimalFormat().decimalFormatSymbols.apply {
            this.decimalSeparator = this@Money.currency.decimalSeparator
            this.groupingSeparator = this@Money.currency.groupingSeparator
        }

        val decimalFormat = DecimalFormat(pattern).apply {
            this.decimalFormatSymbols = decimalFormatSymbols
            this.isGroupingUsed = this@Money.currency.useGroupingSeparator
        }

        val formattedNumber = decimalFormat.format(amount)

        return if (currency.isSymbolPrefix) {
            "${currency.symbol}$formattedNumber"
        } else {
            "$formattedNumber ${currency.symbol}"
        }
    }
}