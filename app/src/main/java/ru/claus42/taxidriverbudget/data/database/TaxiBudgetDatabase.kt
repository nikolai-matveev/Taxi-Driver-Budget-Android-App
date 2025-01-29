package ru.claus42.taxidriverbudget.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.claus42.taxidriverbudget.data.database.converter.Converters

import ru.claus42.taxidriverbudget.data.database.dao.FinancesDao
import ru.claus42.taxidriverbudget.data.database.dao.GoalsDao
import ru.claus42.taxidriverbudget.data.database.entity.FinanceOperationEntity
import ru.claus42.taxidriverbudget.data.database.entity.GoalEntity

@TypeConverters(Converters::class)
@Database(
    entities = [
        FinanceOperationEntity::class,
        GoalEntity::class,
    ],
    version = TaxiBudgetDatabase.DB_VERSION,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
)
abstract class TaxiBudgetDatabase : RoomDatabase() {

    abstract fun financesDao(): FinancesDao
    abstract fun goalsDao(): GoalsDao

    companion object {
        const val DB_VERSION = 2
    }
}