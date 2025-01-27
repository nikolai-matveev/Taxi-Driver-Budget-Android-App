package ru.claus42.taxidriverbudget.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.claus42.taxidriverbudget.domain.model.CategoryType
import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceFlowType
import java.time.ZonedDateTime
import java.util.UUID

const val OPERATION_TABLE_NAME = "financeOperations"
const val OPERATION_ID = "operationId"
const val OPERATION_VALUE = "operationValue"
const val OPERATION_CURRENCY = "operationCurrency"
const val OPERATION_FLOW_TYPE = "operationFlowType"
const val OPERATION_CATEGORY_TYPE = "operationCategoryType"
const val OPERATION_DATE = "operationDate"

@Entity(tableName = OPERATION_TABLE_NAME)
data class FinanceOperationEntity(

    @PrimaryKey
    @ColumnInfo(name = OPERATION_ID)
    val id: UUID,

    @ColumnInfo(name = OPERATION_VALUE)
    val amountInCents: Long,

    @ColumnInfo(name = OPERATION_CURRENCY)
    val currency: Currency,

    @ColumnInfo(name = OPERATION_FLOW_TYPE)
    val flowType: FinanceFlowType,

    @ColumnInfo(name = OPERATION_CATEGORY_TYPE)
    val categoryType: CategoryType,

    @ColumnInfo(name = OPERATION_DATE)
    val date: ZonedDateTime,
)


