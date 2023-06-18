/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.listener

import androidx.compose.material.ModalBottomSheetValue

class EventListener : BaseEventListener()

abstract class BaseEventListener {
    private var appEvent: AppToScreenListener? = null
    private var bottomSheetChangeListener: BottomSheetChangeListener? = null
    private var screenToAppListener: ScreenToAppListener? = null


    //region app event
    fun addOnAppEventListener(listener: AppToScreenListener) {
        appEvent = listener
    }

    fun sendEventToApp(eventName: String, vararg params: String) {
        appEvent?.onEvent(eventName, *params)
    }

    //end region
    //region app event
    fun addOnScreenEventListener(listener: ScreenToAppListener) {
        screenToAppListener = listener
    }

    fun sendEventToScreen(eventName: String, vararg param: String) {
        screenToAppListener?.onEvent(eventName,*param)
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
