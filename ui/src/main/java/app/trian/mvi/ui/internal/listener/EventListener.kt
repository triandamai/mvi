/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.internal.listener

import androidx.compose.material.ModalBottomSheetValue

class EventListener : BaseEventListener()

abstract class BaseEventListener {
    private var appEvent: AppToScreenListener? = null
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

    fun clear() {
        appEvent = null
    }


}
