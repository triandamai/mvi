/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.signUp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

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