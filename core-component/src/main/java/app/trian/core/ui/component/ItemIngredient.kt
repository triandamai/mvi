/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteSweep
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.core.ui.BaseMainApp
import app.trian.core.ui.component.R

@Composable
fun ItemIngredient(
    isDragging: Boolean = false,
    value: String = "",
    onChange: (String) -> Unit = {},
    onRemove: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val inputWidth = (currentWidth) - 100.dp
    val elevation = animateDpAsState(
        if (isDragging) 8.dp else 0.dp,
        label = ""
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation.value)
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp,
                top = 6.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_drag),
                contentDescription = "",
                modifier = Modifier.width(30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            FormInput(
                initialValue = value,
                placeholder = "Ingredient",
                onChange = onChange,
                modifier = Modifier.width(inputWidth)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Outlined.DeleteSweep,
                contentDescription = "",
                modifier = Modifier
                    .width(30.dp)
                    .clickable(
                        enabled = true,
                        onClick = onRemove
                    )
            )
        }
    }
}

@Preview
@Composable
fun PreviewItemIngredient() {
    BaseMainApp {
        ItemIngredient(

        )
    }
}