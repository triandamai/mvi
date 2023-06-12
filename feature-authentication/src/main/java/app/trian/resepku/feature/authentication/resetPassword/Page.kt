/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.resetPassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.core.ui.BaseMainApp
import app.trian.core.ui.BaseScreen
import app.trian.core.ui.R
import app.trian.core.ui.UIListener
import app.trian.core.ui.UIWrapper
import app.trian.core.ui.component.ButtonPrimary
import app.trian.core.ui.component.FormInput


@Composable
fun ScreenResetPassword(
    uiEvent: UIListener<ResetPasswordState, ResetPasswordEvent>
) = UIWrapper(
    uiEvent = uiEvent
) {
    BaseScreen(
        topAppBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            FormInput(
                label = {
                    Text(
                        text = stringResource(id = R.string.label_input_email),
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                placeholder = stringResource(R.string.placeholder_email_reset_password),
                initialValue = state.email,
                onChange = {
                    commit { copy(email = it) }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        hideKeyboard()
                        dispatch(ResetPasswordEvent.Submit)
                    }
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            ButtonPrimary(
                text = stringResource(R.string.btn_reset_password)
            ) {
                hideKeyboard()
                dispatch(ResetPasswordEvent.Submit)
            }
        }
    }


}


@Preview
@Composable
fun PreviewScreenSignIn() {
    BaseMainApp {
        ScreenResetPassword(
            uiEvent = UIListener(
                controller = it,
                state = ResetPasswordState()
            )
        )
    }
}

