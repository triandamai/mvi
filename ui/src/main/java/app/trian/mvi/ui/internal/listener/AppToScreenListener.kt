/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.internal.listener


fun interface AppToScreenListener {
    fun onEvent(eventName: String, vararg params: Any)
}