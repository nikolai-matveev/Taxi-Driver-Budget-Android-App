package ru.claus42.taxidriverbudget.feature.finance.model

import ru.claus42.taxidriverbudget.domain.model.CategoryType
import ru.claus42.taxidriverbudget.domain.model.Currency
import java.util.UUID

data class Operation(
    val uuid: UUID,
    val categoryType: CategoryType,
    val value: Int,
    val currency: Currency,
)