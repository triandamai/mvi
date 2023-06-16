/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui.listener

import android.widget.Toast
import androidx.annotation.RawRes

interface ToastListener {
    fun showToast(message:String,length:Int = Toast.LENGTH_SHORT)
    fun showToast(@RawRes message:Int,vararg params:String,length:Int = Toast.LENGTH_SHORT)
}

class ToastListenerImpl():ToastListener {
    override fun showToast(message: String, length: Int) {

    }

    override fun showToast(message: Int, vararg params: String, length: Int) {

    }
}