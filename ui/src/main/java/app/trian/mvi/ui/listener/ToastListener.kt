/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.listener

import android.widget.Toast
import androidx.annotation.StringRes

interface ToastListener {
    fun showToast(message:String,length:Int = Toast.LENGTH_SHORT)
    fun showToast(@StringRes message:Int,vararg params:String,length:Int = Toast.LENGTH_SHORT)
}

class ToastListenerImpl():ToastListener {
    override fun showToast(message: String, length: Int) {

    }

    override fun showToast(message: Int, vararg params: String, length: Int) {

    }
}