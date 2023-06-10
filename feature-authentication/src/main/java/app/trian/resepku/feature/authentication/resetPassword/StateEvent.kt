/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.resetPassword

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ResetPasswordState(
    val email: String = "",

    val isLoading:Boolean=false
) : Parcelable

@Immutable
sealed class ResetPasswordEvent {
    object Submit : ResetPasswordEvent()
}