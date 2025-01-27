package ru.claus42.taxidriverbudget.domain.model

enum class Currency(
    val symbol: String,
    val isSymbolPrefix: Boolean,
    val useGroupingSeparator: Boolean,
    val groupingSeparator: Char,
    val decimalSeparator: Char = '.',
    val showFractionDigits: Boolean
) {
    RUB(
        symbol = "₽",
        isSymbolPrefix = false,
        useGroupingSeparator = true,
        groupingSeparator = ' ',
        showFractionDigits = false,
    )
}