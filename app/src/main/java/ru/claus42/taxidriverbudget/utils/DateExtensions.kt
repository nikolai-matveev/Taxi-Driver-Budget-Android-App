package ru.claus42.taxidriverbudget.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.Locale

fun ZonedDateTime.toLocalizedMonthName(locale: Locale = Locale.getDefault()): String {
    return month.getDisplayName(TextStyle.SHORT, locale)
}

fun ZonedDateTime.toLocalizedShort(locale: Locale = Locale.getDefault()): String {
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale)
    return format(formatter)
}


fun ZonedDateTime.toLocalizedDayAndMonthNumeric(locale: Locale = Locale.getDefault()): String {
    val pattern = when (locale) {
        Locale.UK, Locale.FRENCH          -> "dd/MM"
        Locale.US, Locale.JAPANESE          -> "MM/dd"
        else               -> "dd.MM"
    }
    val formatter = DateTimeFormatter.ofPattern(pattern, locale)
    return this.format(formatter)
}


fun ZonedDateTime.toLocalizedDayMonthYear(locale: Locale = Locale.getDefault()): String {
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale)
    return format(formatter)
}

fun ZonedDateTime.toLocalizedDayAndMonthName(locale: Locale = Locale.getDefault()): String {
    val day = this.dayOfMonth
    val monthName = this.month.getDisplayName(TextStyle.FULL, locale)
    return "$day $monthName"
}


fun ZonedDateTime.toStartOfDay(): ZonedDateTime {
    return this.truncatedTo(ChronoUnit.DAYS)
}

fun ZonedDateTime.getMonthAndYear(): ZonedDateTime {
    return this.withDayOfMonth(1)
        .truncatedTo(ChronoUnit.DAYS)
}

fun ZonedDateTime.getWeekInterval(
    locale: Locale = Locale.getDefault()
): Pair<ZonedDateTime, ZonedDateTime> {
    val weekFields = WeekFields.of(locale)
    val firstDayOfWeek = weekFields.firstDayOfWeek

    val startOfWeek = this
        .with(TemporalAdjusters.previousOrSame(firstDayOfWeek))
        .withHour(0).withMinute(0).withSecond(0).withNano(0)

    val endOfWeek = startOfWeek
        .plusDays(6)
        .withHour(23).withMinute(59).withSecond(59).withNano(999_999_999)

    return Pair(startOfWeek, endOfWeek)
}

fun ZonedDateTime.getYearInterval(): Pair<ZonedDateTime, ZonedDateTime> {
    val startOfYear = this
        .with(TemporalAdjusters.firstDayOfYear())
        .withHour(0).withMinute(0).withSecond(0).withNano(0)

    val endOfYear = this
        .with(TemporalAdjusters.lastDayOfYear())
        .withHour(23).withMinute(59).withSecond(59).withNano(999_999_999)

    return Pair(startOfYear, endOfYear)
}