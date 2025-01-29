package ru.claus42.taxidriverbudget.data.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.claus42.taxidriverbudget.data.database.TaxiBudgetDatabase
import ru.claus42.taxidriverbudget.data.database.dao.FinancesDao
import ru.claus42.taxidriverbudget.data.database.dao.GoalsDao
import javax.inject.Singleton


private const val DATABASE_NAME = "taxi-budget-database"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideTaxiBudgetDatabase(
        @ApplicationContext context: Context
    ): TaxiBudgetDatabase {
        return Room.databaseBuilder(
            context,
            TaxiBudgetDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFinancesDao(database: TaxiBudgetDatabase): FinancesDao {
        return database.financesDao()
    }

    @Provides
    @Singleton
    fun provideGoalsDao(database: TaxiBudgetDatabase): GoalsDao {
        return database.goalsDao()
    }
}