/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.resepku.base.listener

import androidx.compose.material3.SheetValue

fun interface BottomSheetStateListener {
    fun onStateChanges(state:SheetValue)
}