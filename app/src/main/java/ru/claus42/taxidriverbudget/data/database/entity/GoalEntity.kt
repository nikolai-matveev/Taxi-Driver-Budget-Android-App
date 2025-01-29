package ru.claus42.taxidriverbudget.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.claus42.taxidriverbudget.domain.model.Currency
import java.time.ZonedDateTime

const val GOALS_TABLE_NAME = "financialGoals"

@Entity(GOALS_TABLE_NAME)
data class GoalEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime,
    val expectedProfitInCents: Long,
    val currency: Currency,
)