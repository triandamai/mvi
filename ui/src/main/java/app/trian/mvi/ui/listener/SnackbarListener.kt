/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.ui.listener

import androidx.compose.material3.SnackbarDuration

interface SnacbarListener {
    fun showSnackbar(message:String)
    fun showSnackbar(message:String,duration:SnackbarDuration)

    fun showSnackbar(id:Int,vararg params:String)
    fun showSnackbar(id:Int,vararg params:String,snackbarDuration: SnackbarDuration)
}

class SnackbarListenerImpl():SnacbarListener{
    override fun showSnackbar(message: String) {

    }

    override fun showSnackbar(message: String, duration: SnackbarDuration) {

    }

    override fun showSnackbar(id: Int, vararg params: String) {

    }

    override fun showSnackbar(id: Int, vararg params: String, snackbarDuration: SnackbarDuration) {

    }

}