package app.trian.mvi.ui.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Date Utils
 * author Trian Damai
 * created_at 30/01/22 - 21.32
 * site https://trian.app
 */


fun LocalDate?.format(pattern: String = "d MMMM, yyyy", orElse: String = "Date not valid"): String {
    if (this == null) return orElse
    return this.formatDate(pattern.ifEmpty { "d MMMM, yyyy" })
}

fun LocalDate.formatDate(pattern: String = String.Empty): String {
    if (pattern.isBlank()) {
        return this.format(DateTimeFormatter.BASIC_ISO_DATE)
    }
    return this.format(DateTimeFormatter.ofPattern(pattern))
}