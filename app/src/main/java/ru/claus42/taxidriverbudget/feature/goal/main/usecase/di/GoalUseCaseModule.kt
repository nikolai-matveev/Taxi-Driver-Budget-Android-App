package ru.claus42.taxidriverbudget.feature.goal.main.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.claus42.taxidriverbudget.feature.goal.main.usecase.GetGoalProgressUseCase
import ru.claus42.taxidriverbudget.feature.goal.main.usecase.GetGoalProgressUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface GoalUseCaseModule {
    @Binds
    fun bindGetGoalUseCase(impl: GetGoalProgressUseCaseImpl): GetGoalProgressUseCase
}