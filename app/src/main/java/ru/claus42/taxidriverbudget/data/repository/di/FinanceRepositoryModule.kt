package ru.claus42.taxidriverbudget.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.claus42.taxidriverbudget.data.repository.FinanceRepositoryImpl
import ru.claus42.taxidriverbudget.data.repository.GoalRepositoryImpl
import ru.claus42.taxidriverbudget.domain.repository.FinanceRepository
import ru.claus42.taxidriverbudget.domain.repository.GoalRepository

@Module
@InstallIn(SingletonComponent::class)
interface FinanceRepositoryModule {
    @Binds
    fun bindFinanceRepository(impl: FinanceRepositoryImpl): FinanceRepository

    @Binds
    fun bindGoalRepository(impl: GoalRepositoryImpl): GoalRepository
}