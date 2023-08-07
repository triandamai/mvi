package app.trian.mvi.ui.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun Context.hideKeyboard() {
    val activity = (this as Activity)
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

@SuppressLint("QueryPermissionsNeeded")
fun Context.emailTo(from: String = "", to: String, subject: String) {

    Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$to?subject=$subject&body=$subject - ")
        putExtra(Intent.EXTRA_EMAIL, from)
    }.also { intent ->
        this.startActivity(intent)

    }
}

fun Context.openApplicationOnStore() {
    try {
        Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")).apply {
            addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            )
        }.also {
            this.startActivity(it)
        }
    } catch (e: ActivityNotFoundException) {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
        ).also {
            this.startActivity(it)
        }

    }
}

fun Context.getScreenWidth(): Dp =
    with(this.resources.displayMetrics) { widthPixels.dp / density }

fun Context.getScreenHeight(): Dp =
    with(this.resources.displayMetrics) { heightPixels.dp / density }
