package ru.claus42.taxidriverbudget.feature.finance.screen.main.model

import ru.claus42.taxidriverbudget.domain.model.CategoryType
import ru.claus42.taxidriverbudget.domain.model.Money
import java.util.UUID

data class Operation(
    val uuid: UUID,
    val categoryType: CategoryType,
    val money: Money,
)