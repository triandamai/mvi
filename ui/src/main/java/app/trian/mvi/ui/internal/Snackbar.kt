package app.trian.mvi.ui.internal

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import app.trian.mvi.ui.internal.listener.SnackbarListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Snackbar(
    private val snackbarHostState: SnackbarHostState,
    private val context: Context,
    private val coroutineScope: CoroutineScope
) : SnackbarListener {
    override fun show(message: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message,
                duration = SnackbarDuration.Short
            )
        }
    }

    override fun show(message: String, duration: SnackbarDuration) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message,
                duration = duration
            )
        }
    }

    override fun show(@StringRes stringId: Int, vararg params: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                context.getString(stringId, *params),
                duration = SnackbarDuration.Short
            )
        }
    }

    override fun show(@StringRes stringId: Int, vararg params: String, duration: SnackbarDuration) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                context.getString(stringId, *params),
                duration = duration
            )
        }
    }
}