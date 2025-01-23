package ru.claus42.taxidriverbudget.data.database.converter

import androidx.room.TypeConverter
import ru.claus42.taxidriverbudget.domain.model.CategoryType
import ru.claus42.taxidriverbudget.domain.model.Currency
import ru.claus42.taxidriverbudget.domain.model.FinanceFlowType
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

class Converters {
    @TypeConverter
    fun fromUUID(value: String): UUID {
        return UUID.fromString(value)
    }

    @TypeConverter
    fun uuidToString(uuid: UUID): String {
        return uuid.toString()
    }

    @TypeConverter
    fun fromZonedDateTime(value: String): ZonedDateTime {
        return ZonedDateTime.parse(value, DateTimeFormatter.ISO_ZONED_DATE_TIME)
    }

    @TypeConverter
    fun zonedDateTimeToString(date: ZonedDateTime): String {
        return date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
    }

    @TypeConverter
    fun fromCurrency(value: String): Currency {
        return Currency.valueOf(value)
    }

    @TypeConverter
    fun currencyToString(currency: Currency): String {
        return currency.name
    }

    @TypeConverter
    fun fromFinanceFlowType(value: String): FinanceFlowType {
        return FinanceFlowType.valueOf(value)
    }

    @TypeConverter
    fun financeFlowTypeToString(type: FinanceFlowType): String {
        return type.name
    }

    @TypeConverter
    fun fromCategoryType(value: String): CategoryType {
        return CategoryType.valueOf(value)
    }

    @TypeConverter
    fun categoryTypeToString(type: CategoryType): String {
        return type.name
    }
}