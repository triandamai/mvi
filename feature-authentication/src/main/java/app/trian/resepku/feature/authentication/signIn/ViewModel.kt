/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.signIn

import android.util.Patterns
import app.trian.core.ui.extensions.hideKeyboard
import app.trian.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(

) : BaseViewModel<SignInState, SignInEvent>(SignInState()) {

    init {
        handleActions()
    }

    private fun validateData(
        valid: suspend (String, String) -> Unit
    ) = asyncWithState {
        controller.context.hideKeyboard()
        when {
            email.isEmpty() || password.isEmpty() -> {
                commit {
                    copy(
                        emailIsError = email.isEmpty(),
                        passwordIsError = password.isEmpty()
                    )
                }
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                commit { copy(emailIsError = true) }
            }


            else -> {
                valid(email, password)
            }
        }
    }

    override fun handleActions() = onEvent { event ->
        when (event) {
            SignInEvent.SignInWithEmail -> validateData { email, password ->

            }

            is SignInEvent.OnEmailChange -> commit {
                copy(
                    email = event.email,
                    emailIsError = (!Patterns.EMAIL_ADDRESS.matcher(event.email)
                        .matches() or event.email.isEmpty())
                )
            }

            is SignInEvent.OnPasswordChange -> commit {
                copy(password = event.password, passwordIsError = event.password.isEmpty())
            }
        }
    }
}


