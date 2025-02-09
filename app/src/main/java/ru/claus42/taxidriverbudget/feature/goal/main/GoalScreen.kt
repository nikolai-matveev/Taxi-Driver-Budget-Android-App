package ru.claus42.taxidriverbudget.feature.goal.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.claus42.taxidriverbudget.R
import ru.claus42.taxidriverbudget.feature.goal.main.component.GoalBlock
import ru.claus42.taxidriverbudget.feature.goal.main.viewmodel.GoalIntent
import ru.claus42.taxidriverbudget.feature.goal.main.viewmodel.GoalSideEffect
import ru.claus42.taxidriverbudget.feature.goal.main.viewmodel.GoalViewModel
import ru.claus42.taxidriverbudget.ui.component.BigGreenButton
import ru.claus42.taxidriverbudget.ui.modifier.bottomFadingEdge
import ru.claus42.taxidriverbudget.ui.modifier.center
import ru.claus42.taxidriverbudget.ui.modifier.topFadingEdge


@Composable
fun GoalScreen(
    viewModel: GoalViewModel = hiltViewModel(),
    navigateToAddGoalScreen: () -> Unit,
    navigateToEditGoalScreen: (id: Int) -> Unit,
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val lazyListState: LazyListState = rememberLazyListState()

    val fadingColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                vertical = VERTICAL_SCREEN_PADDING,
                horizontal = HORIZONTAL_SCREEN_PADDING,
            ),
    ) {
        if (state.goalsWithProgress.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .topFadingEdge(
                        color = fadingColor,
                        isVisible = lazyListState.canScrollBackward,
                    )
                    .bottomFadingEdge(
                        color = fadingColor,
                        isVisible = lazyListState.canScrollForward,
                    )
                    .weight(1f),
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(45.dp)
            ) {
                items(
                    items = state.goalsWithProgress,
                    key = { it.first.id }
                ) { (goal, progressMoney) ->
                    GoalBlock(
                        progressMoney = progressMoney,
                        goalMoney = goal.expectedProfit,
                        startDate = goal.startDate,
                        endDate = goal.completionDate,
                        onClick = {
                            viewModel.handleIntent(GoalIntent.EditGoalClicked(goal.id))
                        }
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxSize().weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.no_goals),
                    color = Color.Gray
                )
            }
        }
        BigGreenButton(
            text = stringResource(R.string.add_new_goal),
            modifier = Modifier.padding(top = 22.dp),
            onClick = { viewModel.handleIntent(GoalIntent.AddGoalClicked) }
        )
    }


    LaunchedEffect(viewModel.container.sideEffectFlow) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                GoalSideEffect.NavigateToAddGoalScreen -> {
                    navigateToAddGoalScreen()
                }

                is GoalSideEffect.NavigateToEditGoalScreen -> {
                    navigateToEditGoalScreen(sideEffect.id)
                }

                is GoalSideEffect.ShowError -> {
                    //TODO
                }
            }
        }
    }

}

private val VERTICAL_SCREEN_PADDING = 22.dp
private val HORIZONTAL_SCREEN_PADDING = 20.dp