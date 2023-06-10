/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard.signUp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@androidx.compose.runtime.Immutable
@Parcelize
data class SignUpState(
    val displayName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading:Boolean=false,
    val agreeTnc:Boolean=false
) : Parcelable

@androidx.compose.runtime.Immutable
sealed class SignUpEvent {
    object SignUpWithEmail : SignUpEvent()

}