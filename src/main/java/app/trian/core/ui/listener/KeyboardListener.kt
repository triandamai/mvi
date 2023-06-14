/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.core.ui.listener

class KeyboardListenerImpl():KeyboardListener{
    override fun onShowKeyboard() {

    }

    override fun onHideKeyboard() {

    }

}
interface KeyboardListener{
    fun onShowKeyboard()

    fun onHideKeyboard()
}