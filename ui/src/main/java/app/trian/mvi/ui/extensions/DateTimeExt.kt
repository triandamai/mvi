package app.trian.mvi.ui.extensions

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.Locale

/**
 * Date Utils
 * author Trian Damai
 * created_at 30/01/22 - 21.32
 * site https://trian.app
 */


fun LocalDate?.toReadableDate(pattern: String = "d MMMM, yyyy"): String {

    if (this == null) return "Date not valid"
    return this.formatDate(pattern.ifEmpty { "d MMMM, yyyy" })
}


fun getNowMillis(): LocalDate = LocalDate.now()

fun LocalDate.formatDate(pattern: String=String.Empty): String {
    if (pattern.isBlank()) {
        return this.format(DateTimeFormatter.BASIC_ISO_DATE)
    }
    return this.format(DateTimeFormatter.ofPattern(pattern))
}