/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.resetPassword

import android.content.Context
import android.util.Patterns
import app.trian.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    @ApplicationContext context: Context
) : BaseViewModel<ResetPasswordState, ResetPasswordEvent>(context,ResetPasswordState()) {

    init {
        handleActions()
    }

    private fun validateData(
        valid: suspend (Boolean) -> Unit
    ) = asyncWithState {
        when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty() -> {

            }
            else -> valid(true)
        }
    }

    override fun handleActions() = onEvent {

    }
}


