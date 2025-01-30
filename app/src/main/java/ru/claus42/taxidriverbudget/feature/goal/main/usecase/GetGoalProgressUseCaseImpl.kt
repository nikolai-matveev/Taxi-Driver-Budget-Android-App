package ru.claus42.taxidriverbudget.feature.goal.main.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import ru.claus42.taxidriverbudget.domain.model.Money
import ru.claus42.taxidriverbudget.domain.repository.FinanceRepository
import ru.claus42.taxidriverbudget.domain.repository.GoalRepository
import javax.inject.Inject

class GetGoalProgressUseCaseImpl @Inject constructor(
    private val goalRepository: GoalRepository,
    private val financeRepository: FinanceRepository,
) : GetGoalProgressUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getGoalProgressUseCase(id: Int): Flow<Money> {
        return goalRepository.getGoalFlowById(id)
            .flatMapLatest { goal ->
                financeRepository.calculateProfitForCurrency(
                    start = goal.startDate,
                    end = goal.endDate,
                    currency = goal.expectedProfit.currency
                )
            }
            .distinctUntilChanged()
    }
}