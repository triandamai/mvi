/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.BaseMainApp

@Composable
fun ItemQuizOption(
    selected: Boolean = false,
    answer: String = "",
    value: String = "",
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                all = 8.dp
            )
            .clip(MaterialTheme.shapes.medium)
            .background(
                if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surface
            )
            .clickable(
                enabled = true,
                onClick = onClick
            )
            .border(
                width = 1.dp,
                color = Color.DarkGray,
                shape = MaterialTheme.shapes.medium
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        RadioButton(
            selected = selected,
            onClick = { /*TODO*/ },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.surface,
                unselectedColor = Color.DarkGray
            )
        )
        Text(
            text = answer,
            style = MaterialTheme.typography.bodyMedium,
            color =
            if (selected) MaterialTheme.colorScheme.onPrimary
            else Color.DarkGray
        )
    }
}

@Preview
@Composable
fun PreviewItemQuizOption() {
    BaseMainApp {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(
                8.dp
            ),
            content = {
            item{
                ItemQuizOption(
                    answer = "Lobus erectus"
                )
            }
            item{
                ItemQuizOption(
                    answer = "Lobus erectus",
                    selected = true
                )
            }
        })
    }
}