/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.createRecipe.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.resepku.R
import app.trian.resepku.base.BaseMainApp
import app.trian.resepku.base.extensions.dashedBorder
import app.trian.resepku.components.FormInput
import app.trian.resepku.components.FormInputMultiline

@Composable
fun ScreenMain(
    onPickCover: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .clickable(
                    enabled = true,
                    onClick = onPickCover
                )
                .dashedBorder(
                    width = 2.dp,
                    color = Color.DarkGray,
                    shape = MaterialTheme.shapes.large,
                    on = 5.dp,
                    off = 5.dp
                )
                .padding(
                    all = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_placholder_image),
                contentDescription = "",
                modifier = Modifier.size(
                    60.dp
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Add Cover Photo",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "(Up to 12 Mb)",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )

        }
        Spacer(modifier = Modifier.height(40.dp))
        FormInput(
            label = {
                Text(
                    text = "Recipe Name",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            },
            placeholder = "Resepku"
        )

        FormInputMultiline(
            label = {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            },
            placeholder = "Masukkan Deskripsi"
        )
    }

}

@Preview
@Composable
fun PreviewScreenMain() {
    BaseMainApp {
        ScreenMain()
    }
}