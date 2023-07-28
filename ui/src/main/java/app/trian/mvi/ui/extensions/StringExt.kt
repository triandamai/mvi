/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.extensions

import java.text.Normalizer
import java.util.Locale

val String.Companion.Empty
    inline get() = ""

fun String.slugify(replacement: String = "-") = Normalizer
    .normalize(this, Normalizer.Form.NFD)
    .replace("[^\\p{ASCII}]".toRegex(), "")
    .replace("[^a-zA-Z0-9\\s]+".toRegex(), "").trim()
    .replace("\\s+".toRegex(), replacement)
    .lowercase(Locale.getDefault())