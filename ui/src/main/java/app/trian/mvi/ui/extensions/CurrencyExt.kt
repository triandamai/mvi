/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.formatCurrency(
    show: Boolean = true,
    locale: Locale = Locale.forLanguageTag("ID")
): String {
    return if (show) {
        val format = NumberFormat.getCurrencyInstance(locale)
        format.maximumFractionDigits = 0
        format.format(this)
    } else "Rp*********"
}

fun BigDecimal.formatDecimal(pattern:String="#,###"): String = try {
    val decimalFormat = DecimalFormat(pattern)
    decimalFormat.format(this)
} catch (e: Exception) {
    ""
}


