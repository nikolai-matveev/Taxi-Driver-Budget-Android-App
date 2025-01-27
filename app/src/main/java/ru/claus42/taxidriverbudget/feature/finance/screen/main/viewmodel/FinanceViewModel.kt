package ru.claus42.taxidriverbudget.feature.finance.screen.main.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.claus42.taxidriverbudget.domain.repository.FinanceRepository
import ru.claus42.taxidriverbudget.feature.finance.screen.main.model.PeriodType
import ru.claus42.taxidriverbudget.utils.getWeekInterval
import ru.claus42.taxidriverbudget.utils.getYearInterval
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val repository: FinanceRepository,
) : ContainerHost<FinanceScreenState, FinanceSideEffect>, ViewModel() {

    override val container = container<FinanceScreenState, FinanceSideEffect>(
        initialState = FinanceScreenState(
            periodType = PeriodType.WEEK,
            dateInterval = ZonedDateTime.now().getDateInterval(PeriodType.WEEK),
            operations = listOf(),
        )
    ) {
        loadInitialData()
    }

    private var lastWeekInterval: Pair<ZonedDateTime, ZonedDateTime>? = null

    init {
        observeOperations()
        observeLastWeekInterval()
    }



    fun handleIntent(intent: FinanceIntent) = intent {
        when (intent) {
            FinanceIntent.AddOperationClicked -> {
                postSideEffect(FinanceSideEffect.NavigateToAddOperationScreen)
            }
            //todo: handle week/month
            FinanceIntent.BackDate -> {
                reduce { state.copy(dateInterval = state.getPreviousInterval()) }
            }

            FinanceIntent.ForwardDate -> {
                reduce { state.copy(dateInterval = state.getNextInterval()) }
            }

            is FinanceIntent.DateWithIndexClicked -> {
                postSideEffect(FinanceSideEffect.ScrollChartToPosition(intent.index))
            }

            is FinanceIntent.IndexedChartClicked -> {
                postSideEffect(FinanceSideEffect.ScrollOperationListToPosition(intent.index))
            }

            is FinanceIntent.SwitchPeriodTypeTab -> {
                reduce {
                    state.copy(
                        periodType = intent.periodType,
                        dateInterval = state.dateInterval.first.switchPeriodType(intent.periodType)
                    )
                }
            }
        }
    }

    private fun loadInitialData() = intent {
        val (start, end) = state.dateInterval

        repository.getFinanceOperationsInRangeFlow(start, end)
            .onStart {
                reduce { state.copy(isLoading = true) }
            }
            .catch { error ->
                postSideEffect(FinanceSideEffect.ShowError(error.message ?: "Loading Error"))
                reduce { state.copy(isLoading = false) }
            }
            .collect { operations ->
                reduce {
                    state.copy(
                        operations = operations,
                        isLoading = false
                    )
                }
            }
    }

    private fun FinanceScreenState.getPreviousInterval()
            : Pair<ZonedDateTime, ZonedDateTime> {
        val dateFromNewInterval = dateInterval.first.minusHours(1)
        return dateFromNewInterval.getDateInterval(periodType)
    }

    private fun FinanceScreenState.getNextInterval()
            : Pair<ZonedDateTime, ZonedDateTime> {
        val dateFromNewInterval = dateInterval.second.plusHours(1)
        return dateFromNewInterval.getDateInterval(periodType)
    }

    private fun ZonedDateTime.getDateInterval(periodType: PeriodType): Pair<ZonedDateTime, ZonedDateTime> {
        return when (periodType) {
            PeriodType.WEEK -> getWeekInterval()
            PeriodType.MONTH -> getYearInterval()
        }
    }

    private fun ZonedDateTime.switchPeriodType(periodType: PeriodType): Pair<ZonedDateTime, ZonedDateTime> {
        return when (periodType) {
            PeriodType.WEEK -> lastWeekInterval ?: getWeekInterval()
            PeriodType.MONTH -> getYearInterval()
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeOperations() = intent {
        container.stateFlow
            .map { it.dateInterval }
            .distinctUntilChanged()
            .flatMapLatest { (start, end) ->
                repository.getFinanceOperationsInRangeFlow(start, end)
            }
            .onStart { reduce { state.copy(isLoading = true) } }
            .catch { e ->
                reduce { state.copy(isLoading = false) }
            }
            .collect { operations ->
                reduce {
                    state.copy(
                        operations = operations,
                        isLoading = false
                    )
                }
            }
    }

    private fun observeLastWeekInterval() = intent {
        container.stateFlow
            .filter { it.periodType == PeriodType.WEEK }
            .map { it.dateInterval }
            .distinctUntilChanged()
            .collect {
                lastWeekInterval = it
            }
    }
}