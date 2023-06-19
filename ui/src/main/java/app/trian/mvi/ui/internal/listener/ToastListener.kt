/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.internal.listener

import android.widget.Toast
import androidx.annotation.StringRes

interface ToastListener {
    fun show(message:String,length:Int = Toast.LENGTH_SHORT)
    fun show(@StringRes message:Int,vararg params:String,length:Int = Toast.LENGTH_SHORT)
}

