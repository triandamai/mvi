/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.signUp

import android.content.Context
import android.util.Patterns
import app.trian.core.ui.extensions.hideKeyboard
import app.trian.core.ui.extensions.showBottomSheet
import app.trian.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext context: Context
) : BaseViewModel<SignUpState, SignUpEvent>(context, SignUpState()) {

    init {
        handleActions()
    }

    private fun signUpWithEmail(
    ) = asyncWithState {
        when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {

            }

            else -> {

                context.hideKeyboard()
                bottomSheet.showBottomSheet(true)
            }
        }
    }

    override fun handleActions() = onEvent {

    }
}