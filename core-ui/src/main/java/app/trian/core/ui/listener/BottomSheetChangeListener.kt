/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui.listener

import androidx.compose.material.ModalBottomSheetValue

fun interface BottomSheetChangeListener {
    fun onChange(value:ModalBottomSheetValue):Boolean
}