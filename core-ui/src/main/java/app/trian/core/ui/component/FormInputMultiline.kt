/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.core.ui.BaseMainApp

/**
 * FormInpit
 * author Trian Damai
 * created_at 30/01/22 - 09.02
 * site https://trian.app
 */
@Composable
fun FormInputMultiline(
    modifier: Modifier = Modifier,
    initialValue: String = "",
    placeholder: String = "",
    label: @Composable () -> Unit = {},
    maxLines: Int = 3,
    minLines: Int = 3,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onChange: (value: String) -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        label.invoke()
        Spacer(modifier = modifier.height(8.dp))
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = initialValue,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            onValueChange = {
                onChange(it)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            maxLines = maxLines,
            minLines = minLines,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions
        )
        Spacer(modifier = modifier.height(10.dp))
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewFormInputMultiline() {
    BaseMainApp {
        FormInputMultiline(
        )
    }
}