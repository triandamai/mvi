package app.trian.mvi.ui.internal

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import app.trian.mvi.ui.internal.listener.ToastListener

class ToastMvi(
    private val context:Context
):ToastListener {
    override fun show(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    override fun show(message: Int, vararg params: Any) {
        Toast.makeText(context,context.getString(message,params),Toast.LENGTH_SHORT).show()
    }

}