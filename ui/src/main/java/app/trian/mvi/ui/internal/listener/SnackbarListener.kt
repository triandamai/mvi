/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.ui.internal.listener

import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration

interface SnackbarListener {
    fun show(message: String)
    fun show(message: String, duration: SnackbarDuration)

    fun show(@StringRes stringId: Int, vararg params: String)
    fun show(@StringRes stringId: Int, vararg params: String, snackbarDuration: SnackbarDuration)
}