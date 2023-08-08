/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.internal.listener

import android.widget.Toast
import androidx.annotation.StringRes
import java.util.Objects

interface ToastListener {
    fun show(message: String)
    fun show(message: Int, vararg params: Any)
}

