package ru.claus42.taxidriverbudget.data.database.mapper

import ru.claus42.taxidriverbudget.data.database.entity.FinanceOperationEntity
import ru.claus42.taxidriverbudget.domain.model.FinanceOperation
import ru.claus42.taxidriverbudget.domain.model.Money

fun FinanceOperation.toEntity(): FinanceOperationEntity = FinanceOperationEntity(
    id = id,
    amountInCents = money.amountInCents,
    currency = money.currency,
    flowType = flowType,
    categoryType = categoryType,
    date = date,
)

fun FinanceOperationEntity.toDomainModel(): FinanceOperation = FinanceOperation(
    id = id,
    money = Money(amountInCents = amountInCents, currency = currency),
    flowType = flowType,
    categoryType = categoryType,
    date = date,
)