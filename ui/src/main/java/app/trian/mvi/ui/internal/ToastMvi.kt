package app.trian.mvi.ui.internal

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import app.trian.mvi.ui.internal.listener.ToastListener

class ToastMvi(
    private val context:Context
):ToastListener {
    override fun show(message: String, length: Int) {
        Toast.makeText(context,message,length).show()
    }

    override fun show(@StringRes message: Int, vararg params: String, length: Int) {
        Toast.makeText(context,context.getString(message,*params),length).show()
    }

}