/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteSweep
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.core.ui.BaseMainApp
import app.trian.core.ui.R

@Composable
fun ItemCookingStep(
    isDragging: Boolean = false,
    number: String = "",
    value: String = "",
    onCaptureImage: () -> Unit = {},
    onChange: (String) -> Unit = {},
    onRemove: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val inputWidth = (currentWidth) - 60.dp

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
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(
                    fraction = 0.1f
                ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            text = number,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_drag),
                        contentDescription = "",
                        modifier = Modifier.width(30.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
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
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.width(
                    inputWidth
                )
            ) {
                FormInputMultiline(
                    initialValue = value,
                    placeholder = "Input cooking step here...",
                    onChange = onChange,
                    modifier = Modifier.fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .clickable(
                            enabled = true,
                            onClick = onCaptureImage
                        )
                        .background(
                            Color(0xFFF4F5F7)
                        )
                        .padding(
                            vertical = 8.dp
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.Center)
                    )
                }
            }

        }

    }
}

@Preview
@Composable
fun PreviewItemCookingStep() {
    BaseMainApp {
        ItemCookingStep(
            number = "1"
        )
    }
}