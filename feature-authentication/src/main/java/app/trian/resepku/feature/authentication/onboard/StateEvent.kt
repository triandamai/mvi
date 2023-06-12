/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.onboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class OnboardState(
    val percentage:Float=01f
) : Parcelable


sealed interface OnboardEvent{
    data class PagerChanges(val page:Int=0): OnboardEvent

}