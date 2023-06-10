/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class DashboardState(
    val a: String = ""
) : Parcelable

@Immutable
@Parcelize
data class DashboardDataState(
    val a: String = ""
) : Parcelable

sealed interface DashboardEvent {
}