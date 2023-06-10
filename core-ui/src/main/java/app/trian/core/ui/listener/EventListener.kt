/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui.listener

import androidx.compose.material.ModalBottomSheetValue


class EventListener {
    private var appEvent: AppStateEventListener? = null
    private var bottomSheetChangeListener: BottomSheetChangeListener? = null


    //region app event
    fun addOnEventListener(listener: AppStateEventListener) {
        appEvent = listener
    }

    fun sendEvent(eventName: ScreenToAppEvent) {
        appEvent?.onEvent(eventName)
    }
    //end region

    //region bottom sheet
    fun addOnBottomSheetChangeListener(listener: BottomSheetChangeListener) {
        bottomSheetChangeListener = listener
    }

    fun changeBottomSheet(value: ModalBottomSheetValue): Boolean =
        bottomSheetChangeListener?.onChange(value) ?: false

    //end

    fun clear() {
        appEvent = null
    }


}
