/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import java.text.Normalizer
import java.util.Locale

fun String.slugify(replacement: String = "-") = Normalizer
    .normalize(this, Normalizer.Form.NFD)
    .replace("[^\\p{ASCII}]".toRegex(), "")
    .replace("[^a-zA-Z0-9\\s]+".toRegex(), "").trim()
    .replace("\\s+".toRegex(), replacement)
    .lowercase(Locale.getDefault())

@Composable
fun AnnotatedString.Builder.appendButton(
    text: String = "",
    tag: String = "",
    textColor: Color = Color.DarkGray,
    decoration: TextDecoration = TextDecoration.None
) {
    append(" ")
    pushStringAnnotation(
        tag = tag,
        annotation = tag
    )
    withStyle(
        style = SpanStyle(
            color = textColor,
            textDecoration = decoration
        )
    ) {
        append(text)
    }
    pop()
}

