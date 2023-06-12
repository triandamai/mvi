/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui.listener


sealed interface  ScreenToAppEvent {
    object EXIT_APP:ScreenToAppEvent

}


fun interface AppStateEventListener {
    fun onEvent(event:ScreenToAppEvent)
}