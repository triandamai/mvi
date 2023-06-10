/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard.changePassword

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ChangePasswordState(
    val newPassword: String = "",
    val confirmPassword: String = "",

    val isLoading:Boolean=false
) : Parcelable

@Immutable
sealed class ChangePasswordEvent {
    object Submit : ChangePasswordEvent()
}