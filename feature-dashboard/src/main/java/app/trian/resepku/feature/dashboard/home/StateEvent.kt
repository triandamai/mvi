/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class HomeState(
    val percentage:Float=01f
) : Parcelable

@Immutable
@Parcelize
data class HomeDataState(
    val percentage:Float=01f
) : Parcelable


sealed interface HomeEvent{
    data class PagerChanges(val page:Int=0): HomeEvent

}