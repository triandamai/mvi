/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.changePassword

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
fun ScreenChangePassword(
    state: ChangePasswordState = ChangePasswordState(),
    invoker: UIListener<ChangePasswordState, ChangePasswordEvent>
) = UIWrapper(
    invoker
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
        },
        bottomBar = {

        }
    ) {

        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                )
        ) {
            FormInput(
                label = {
                    Text(text = stringResource(R.string.label_input_new_password))
                },
                placeholder = stringResource(R.string.placeholder_input_new_password),
                showPasswordObsecure = true,
                initialValue = state.newPassword,
                onChange = {
                    commit { copy(newPassword = it) }
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            FormInput(
                label = {
                    Text(text = stringResource(R.string.label_input_confirm_new_password))
                },
                placeholder = stringResource(R.string.placeholder_input_confirm_new_password),
                showPasswordObsecure = true,
                initialValue = state.confirmPassword,
                onChange = {
                    commit { copy(confirmPassword = it) }
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        hideKeyboard()
                        dispatch(ChangePasswordEvent.Submit)
                    }
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            ButtonPrimary(
                text = stringResource(R.string.btn_change_password)
            ) {
                hideKeyboard()
                dispatch(ChangePasswordEvent.Submit)
            }
        }
    }


}


@Preview
@Composable
fun PreviewScreenSignIn() {
    BaseMainApp {
        ScreenChangePassword(
            invoker = UIListener(
                controller = it,
                state = ChangePasswordState()
            )
        )
    }
}