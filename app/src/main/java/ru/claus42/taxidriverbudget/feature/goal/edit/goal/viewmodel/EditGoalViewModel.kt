package ru.claus42.taxidriverbudget.feature.goal.edit.goal.viewmodel

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.Goal
import ru.claus42.taxidriverbudget.domain.model.Money
import ru.claus42.taxidriverbudget.domain.repository.GoalRepository
import ru.claus42.taxidriverbudget.utils.toEndOfDay
import ru.claus42.taxidriverbudget.utils.toStartOfDay
import timber.log.Timber
import java.time.ZonedDateTime


@HiltViewModel(assistedFactory = EditGoalViewModel.Factory::class)
class EditGoalViewModel @AssistedInject constructor(
    @Assisted private val id: Int?,
    private val repository: GoalRepository,
) : ContainerHost<EditGoalScreenState, EditGoalSideEffect>, ViewModel() {
    override val container = container<EditGoalScreenState, EditGoalSideEffect>(
        initialState = EditGoalScreenState(
            startDate = ZonedDateTime.now().toStartOfDay(),
            completionDate = ZonedDateTime.now().plusDays(7).toEndOfDay(),
            amountForPeriod = null,
            currency = Currency.RUB,
        )
    ) {
        id?.let(::loadInitialData)
    }

    fun handleIntent(intent: EditGoalIntent) = intent {
        when (intent) {
            EditGoalIntent.AddGoalClicked -> {
                try {
                    saveGoal(state)
                    postSideEffect(EditGoalSideEffect.AddNewGoal)
                } catch (e: Exception) {
                    //todo
                    Timber.e(e)
                }
            }
            is EditGoalIntent.SelectStartDate -> {
                reduce { state.copy(startDate = intent.date.toStartOfDay()) }
            }
            is EditGoalIntent.SelectCompletionDate -> {
                reduce { state.copy(completionDate = intent.date.toEndOfDay()) }
            }
            is EditGoalIntent.SetOperationValue -> {
                reduce { state.copy(amountForPeriod = intent.amountForPeriod) }
            }
        }
    }


    private suspend fun saveGoal(state: EditGoalScreenState) {
        val goal = Goal(
            id = id ?: 0,
            startDate = state.startDate,
            completionDate = state.completionDate,
            expectedProfit = Money(
                amountInCents = state.amountForPeriod ?: 0,
                currency = state.currency,
            ),
        )

        repository.insert(goal)
    }


    private fun loadInitialData(id: Int) = intent {
        val goal = repository.getGoalById(id)

        reduce {
            state.copy(
                startDate = goal.startDate,
                completionDate = goal.completionDate,
                amountForPeriod = goal.expectedProfit.amountInCents,
                currency = goal.expectedProfit.currency,
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int?): EditGoalViewModel
    }

}