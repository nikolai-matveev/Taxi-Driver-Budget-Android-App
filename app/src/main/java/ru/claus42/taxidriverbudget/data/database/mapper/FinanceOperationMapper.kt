package ru.claus42.taxidriverbudget.data.database.mapper

import ru.claus42.taxidriverbudget.data.database.entity.FinanceOperationEntity
import ru.claus42.taxidriverbudget.domain.model.FinanceOperation

fun FinanceOperation.toEntity(): FinanceOperationEntity = FinanceOperationEntity(
    id = id,
    value = value,
    currency = currency,
    flowType = flowType,
    categoryType = categoryType,
    date = date,
)

fun FinanceOperationEntity.toDomainModel(): FinanceOperation = FinanceOperation(
    id = id,
    value = value,
    currency = currency,
    flowType = flowType,
    categoryType = categoryType,
    date = date,
)