package ru.claus42.taxidriverbudget.feature.goal.main.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.claus42.taxidriverbudget.domain.repository.GoalRepository
import ru.claus42.taxidriverbudget.feature.goal.main.usecase.GetGoalProgressUseCase
import ru.claus42.taxidriverbudget.utils.flattenFlow
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val goalProgressUseCase: GetGoalProgressUseCase,
) : ContainerHost<GoalScreenState, GoalSideEffect>, ViewModel() {

    override val container = container<GoalScreenState, GoalSideEffect>(
        initialState = GoalScreenState(
            goalsWithProgress = listOf(),
            isLoading = true,
        )
    )

    init {
        observeGoalsWithProgress()
    }

    fun handleIntent(intent: GoalIntent) = intent {
        when (intent) {
            GoalIntent.AddGoalClicked -> {
                postSideEffect(GoalSideEffect.NavigateToAddGoalScreen)
            }

            is GoalIntent.EditGoalClicked -> {
                postSideEffect(GoalSideEffect.NavigateToEditGoalScreen(intent.id))
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeGoalsWithProgress() = intent {

        goalRepository.allGoalsFlow()
            .onStart {
                reduce { state.copy(isLoading = true) }
            }
            .catch { error ->
                postSideEffect(GoalSideEffect.ShowError(error.message ?: "Loading Error"))
                reduce { state.copy(isLoading = false) }
            }
            .flatMapLatest { goals ->
                if (goals.isEmpty()) {
                    flowOf(emptyList())
                } else {
                    goals.map { goal ->
                        goalProgressUseCase.getGoalProgressUseCase(goal.id)
                            .map { progress -> goal to progress }
                    }.flattenFlow()
                }
            }
            .collect { goalsWithProgress ->
                reduce {
                    state.copy(
                        goalsWithProgress = goalsWithProgress,
                        isLoading = false,
                    )
                }
            }
    }

}