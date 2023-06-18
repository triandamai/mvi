/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.BaseMainApp

@Composable
fun BottomSheetConfirmation(
    title: String = "",
    message: String = "",
    textConfirmation: String = "Confirm",
    textCancel: String = "Confirm",
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val buttonWidth = (currentWidth / 2) - 28.dp

    BaseBottomSheet(
        showButtonConfirmation = false,
        showLineHeader = true,
        onDismiss = onDismiss
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                ButtonSecondary(
                    text = textCancel,
                    fullWidth = false,
                    modifier = Modifier.width(buttonWidth),
                    onClick = onDismiss
                )
                ButtonPrimary(
                    text = textConfirmation,
                    fullWidth = false,
                    modifier = Modifier.width(buttonWidth),
                    onClick = onConfirm
                )
            }
        }

    }


}


@Preview
@Composable
fun PreviewBottomSheetConfirmation() {
    BaseMainApp {
        BottomSheetConfirmation(
            title = "Yakin hapus transaski ini?",
            message = "Data transaksi yang telah kamu isi akan hilang dari catatan transaksi kamu",
            textConfirmation = "Hapus",
            textCancel = "Batal"
        )
    }
}