/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.signUp

import android.util.Patterns
import app.trian.core.ui.extensions.hideKeyboard
import app.trian.core.ui.extensions.showBottomSheet
import app.trian.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : BaseViewModel<SignUpState, SignUpEvent>(SignUpState()) {

    init {
        handleActions()
    }

    private fun signUpWithEmail(
    ) = asyncWithState {
        when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {

            }

            else -> {

                controller.context.hideKeyboard()
                controller.showBottomSheet()
            }
        }
    }

    override fun handleActions() = onEvent {

    }
}