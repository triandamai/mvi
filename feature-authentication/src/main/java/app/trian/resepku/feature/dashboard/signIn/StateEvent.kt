/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard.signIn

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class SignInState(
    val email: String = "",
    val password: String = "",
    var emailIsError: Boolean=false,
    var passwordIsError: Boolean=false,
) : Parcelable

sealed class SignInEvent {
    object SignInWithEmail: SignInEvent()
    class OnEmailChange(var email: String) : SignInEvent()
    class OnPasswordChange(var password: String) : SignInEvent()
}