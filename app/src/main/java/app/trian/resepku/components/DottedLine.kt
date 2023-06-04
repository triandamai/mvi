/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp

@Composable
fun DottedLine(
    color: Color = MaterialTheme.colorScheme.primary
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f)
    Canvas(
        modifier = Modifier.fillMaxWidth().height(1.dp)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }

}