package app.trian.mvi.ui.internal

import android.content.Context
import app.trian.mvi.ui.extensions.hideKeyboard
import app.trian.mvi.ui.internal.listener.KeyboardListener

class Keyboard(
   private val context: Context
):KeyboardListener {
    override fun hide()  = context.hideKeyboard()

}