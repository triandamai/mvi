/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.changePassword

import android.content.Context
import app.trian.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    @ApplicationContext context: Context
) : BaseViewModel<ChangePasswordState, ChangePasswordEvent>(context,ChangePasswordState()) {

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