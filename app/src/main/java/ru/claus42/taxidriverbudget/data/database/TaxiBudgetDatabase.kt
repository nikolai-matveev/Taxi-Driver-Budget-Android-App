package ru.claus42.taxidriverbudget.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.claus42.taxidriverbudget.data.database.converter.Converters

import ru.claus42.taxidriverbudget.data.database.dao.FinancesDao
import ru.claus42.taxidriverbudget.data.database.entity.FinanceOperationEntity

@TypeConverters(Converters::class)
@Database(
    entities = [
        FinanceOperationEntity::class,
    ],
    version = TaxiBudgetDatabase.DB_VERSION,
    exportSchema = true,
)
abstract class TaxiBudgetDatabase : RoomDatabase() {

    abstract fun financesDao(): FinancesDao

    companion object {
        const val DB_VERSION = 1
    }
}