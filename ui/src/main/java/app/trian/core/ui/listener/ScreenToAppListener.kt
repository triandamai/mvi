/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui.listener


fun interface ScreenToAppListener {
    fun onEvent(eventName: String, vararg params: String)
}