/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.signIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import app.trian.core.ui.UIListener
import app.trian.core.ui.UIWrapper
import app.trian.core.ui.component.AnnotationTextItem
import app.trian.core.ui.rememberUIController
import app.trian.core.ui.R.string
import app.trian.core.ui.component.ButtonPrimary
import app.trian.core.ui.component.ButtonSocial
import app.trian.core.ui.component.FormInput
import app.trian.core.ui.component.TextWithAction
import app.trian.core.ui.routes.Routes


@Composable
fun ScreenSignIn(
    invoker: UIListener<SignInState, SignInEvent>
) = UIWrapper(
    invoker = invoker
) {
    val forgetPasswordText = listOf(
        AnnotationTextItem.Text(stringResource(id = string.label_forgot_password)),
        AnnotationTextItem.Button(stringResource(id = string.btn_reset_here))
    )

    val signUp = listOf(
        AnnotationTextItem.Text(stringResource(id = string.label_dont_have_account)),
        AnnotationTextItem.Button(stringResource(id = string.btn_create_new_account))
    )

    BaseScreen(
        topAppBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {

                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp
                    ),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(string.title_sign_in),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(string.subtitle_signin),
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(36.dp))
                FormInput(
                    label = {
                        Text(
                            text = stringResource(string.label_input_email),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    initialValue = state.email,
                    placeholder = stringResource(string.placeholder_input_email),
                    onChange = {
                        commit { copy(email = it) }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                FormInput(
                    label = {
                        Text(
                            text = stringResource(string.label_input_password),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    initialValue = state.password,
                    placeholder = stringResource(string.placeholder_input_password),
                    onChange = {
                        commit { copy(password = it) }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            hideKeyboard()
                            dispatch(SignInEvent.SignInWithEmail)
                        }
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
                ButtonPrimary(text = stringResource(id = string.btn_signin)) {
                    hideKeyboard()
                    dispatch(SignInEvent.SignInWithEmail)
                }
                Spacer(modifier = Modifier.height(10.dp))
                TextWithAction(
                    labels = forgetPasswordText,
                    onTextClick = {
                        if (it == 1) {
                            navigateSingleTop(Routes.ResetPassword.routeName)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(30.dp))
                Column {
                    Divider()
                    Spacer(modifier = Modifier.height(36.dp))
                    Column(
                        modifier = Modifier.padding(horizontal = 36.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ButtonSocial(
                            text = stringResource(id = string.btn_login_google),
                            fullWidth = true
                        ) {

                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        TextWithAction(
                            labels = signUp,
                            onTextClick = {
                                if (it == 1) {
                                    navigateSingleTop(Routes.SignUp.routeName)
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }


}


@Preview
@Composable
fun PreviewScreenSignIn() {
    BaseMainApp {
        ScreenSignIn(
            invoker = UIListener(
                controller = rememberUIController(),
                state = SignInState()
            )
        )
    }
}

