package ru.claus42.taxidriverbudget.data.database.mapper

import ru.claus42.taxidriverbudget.data.database.entity.GoalEntity
import ru.claus42.taxidriverbudget.domain.model.Goal
import ru.claus42.taxidriverbudget.domain.model.Money

fun Goal.toEntity(): GoalEntity = GoalEntity(
    id = id,
    startDate = startDate,
    endDate = completionDate,
    expectedProfitInCents = expectedProfit.amountInCents,
    currency = expectedProfit.currency,
)

fun GoalEntity.toDomainModel(): Goal = Goal(
    id = id,
    startDate = startDate,
    completionDate = endDate,
    expectedProfit = Money(expectedProfitInCents, currency),
)