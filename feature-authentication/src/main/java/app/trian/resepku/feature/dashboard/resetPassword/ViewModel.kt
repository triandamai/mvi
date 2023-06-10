/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard.resetPassword

import android.util.Patterns
import app.trian.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(

) : BaseViewModel<ResetPasswordState, ResetPasswordEvent>(ResetPasswordState()) {

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


