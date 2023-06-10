/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard.changePassword

import app.trian.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(

) : BaseViewModel<ChangePasswordState, ChangePasswordEvent>(ChangePasswordState()) {

    init {
        handleActions()
    }

    private fun validateInputOldPassword(
        valid: suspend (Boolean, String) -> Unit,
    ) = asyncWithState {
        when {
            newPassword.isEmpty() -> valid(false, "")
            else -> valid(true, "")
        }
    }

    private fun validateInputNewPassword(
        valid: suspend (Boolean, String) -> Unit
    ) = asyncWithState {
        when {
            newPassword.isEmpty() -> valid(false, "")
            newPassword.length < 8 -> valid(false, "")
            else -> valid(true, "")
        }
    }

    private fun validateConfirmNewPassword(
        valid: suspend (Boolean, String) -> Unit
    ) = asyncWithState {
        when {
             confirmPassword!= newPassword -> valid(false, "")
            confirmPassword.isEmpty() -> valid(false, "")
            else -> valid(true, "")
        }
    }



    override fun handleActions() = onEvent {
        when (it) {
            ChangePasswordEvent.Submit -> TODO()
        }
    }
}