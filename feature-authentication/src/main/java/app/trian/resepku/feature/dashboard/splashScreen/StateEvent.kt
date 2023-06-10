/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard.splashScreen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class SplashState(
    val a:String=""
) : Parcelable

sealed class SplashEvent{
    object CheckSession: SplashEvent()
}