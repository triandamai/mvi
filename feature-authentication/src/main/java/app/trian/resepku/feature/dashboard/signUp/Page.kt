/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard.signUp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.core.ui.BaseMainApp
import app.trian.core.ui.BaseScreen
import app.trian.core.ui.R.string
import app.trian.core.ui.UIListener
import app.trian.core.ui.UIWrapper
import app.trian.core.ui.component.AnnotationTextItem
import app.trian.core.ui.component.ButtonPrimary
import app.trian.core.ui.component.CheckBoxWithAction
import app.trian.core.ui.component.FormInput
import app.trian.core.ui.component.TextWithAction
import app.trian.core.ui.routes.Routes


@Composable
fun ScreenSignUp(
    invoker: UIListener<SignUpState, SignUpEvent>
) = UIWrapper(
    invoker = invoker
) {
    val privacyPolicy = listOf(
        AnnotationTextItem.Text(stringResource(id = string.text_license_agreement)),
        AnnotationTextItem.Button(stringResource(id = string.text_privacy_policy))
    )
    val signInText = listOf(
        AnnotationTextItem.Text(stringResource(id = string.label_already_have_account)),
        AnnotationTextItem.Button(stringResource(id = string.text_signin))
    )
    BaseScreen(
        controller = invoker.controller,
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
        bottomSheet = {

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp
                )
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(string.title_register),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Row {
                TextWithAction(
                    labels = signInText,
                    onTextClick = {
                        if (it == 1) {
                            navigateAndReplaceAll(Routes.SignIn.routeName)
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                FormInput(
                    initialValue = state.displayName,
                    label = {
                        Text(
                            text = stringResource(string.label_input_display_nama),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    placeholder = stringResource(string.placeholder_input_display_name),
                    onChange = {
                        commit { copy(displayName = it) }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                FormInput(
                    initialValue = state.email,
                    label = {
                        Text(
                            text = stringResource(id = string.label_input_email),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    placeholder = stringResource(id = string.placeholder_input_email),
                    onChange = {
                        commit { copy(email = it) }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                FormInput(
                    initialValue = state.password,
                    label = {
                        Text(
                            text = stringResource(id = string.label_input_password),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    placeholder = stringResource(id = string.placeholder_input_password),
                    onChange = {
                        commit { copy(password = it) }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            hideKeyboard()
                            dispatch(SignUpEvent.SignUpWithEmail)
                        }
                    )
                )
                FormInput(
                    initialValue = state.confirmPassword,
                    label = {
                        Text(
                            text = stringResource(id = string.label_input_confirm_new_password),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    placeholder = stringResource(id = string.placeholder_input_confirm_new_password),
                    onChange = {
                        commit { copy(confirmPassword = it) }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            hideKeyboard()
                            dispatch(SignUpEvent.SignUpWithEmail)
                        }
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CheckBoxWithAction(
                    labels = privacyPolicy,
                    checked = state.agreeTnc,
                    onTextClick = {
                        if (it == 1) {
                            showBottomSheet()
                        }
                    },
                    onCheckedChange = {
                        commit { copy(agreeTnc = it) }
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            ButtonPrimary(
                text = stringResource(string.btn_continue),
                enabled = state.agreeTnc

            ) {
                hideKeyboard()
                dispatch(SignUpEvent.SignUpWithEmail)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Preview
@Composable
fun PreviewScreenSignUp() {
    BaseMainApp {
        ScreenSignUp(
            invoker = UIListener(
                controller = it,
                state = SignUpState()
            )
        )
    }

}