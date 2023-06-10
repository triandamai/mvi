/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui


import android.content.Context
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.core.ui.listener.EventListener
import kotlinx.coroutines.CoroutineScope
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.launch


class CreateSnackbarContent(
    private val content: @Composable (SnackbarData) -> Unit
) {
    @Composable
    fun invoke(snackbarData: SnackbarData) {
        content.invoke(snackbarData)
    }
}

class UIController internal constructor(
    val router: NavHostController,
    val bottomSheetState: ModalBottomSheetState,
    val scope: CoroutineScope,
    val event: EventListener,
    val context: Context
) {

    var currentRoute by mutableStateOf("")

    var snackbarHostState by mutableStateOf(
        SnackbarHostState()
    )

    internal var snackbar by mutableStateOf(CreateSnackbarContent {
        Snackbar(snackbarData = it)
    })

    fun showSnackbar(message: String) {
        runSuspend {
            snackbarHostState.showSnackbar(
                message,
                duration = SnackbarDuration.Short
            )
        }
    }

    fun showSnackbar(message: Int) {
        runSuspend {
            snackbarHostState.showSnackbar(
                context.getString(message),
                duration = SnackbarDuration.Short
            )
        }
    }

    fun showSnackbar(message: Int, vararg params: String) {
        runSuspend {
            snackbarHostState.showSnackbar(
                context.getString(message, *params),
                duration = SnackbarDuration.Short
            )
        }
    }

    fun showSnackbar(message: String, duration: SnackbarDuration) {
        runSuspend {
            snackbarHostState.showSnackbar(
                message,
                duration = duration
            )
        }
    }

    fun showSnackbar(message: String, actionLabel: String, onAction: () -> Unit = {}) {
        runSuspend {
            when (snackbarHostState.showSnackbar(
                message,
                actionLabel = actionLabel
            )) {
                SnackbarResult.Dismissed -> Unit
                SnackbarResult.ActionPerformed -> onAction()
            }
        }
    }

    fun runSuspend(
        block: suspend () -> Unit = {}
    ) = scope.launch {
        block()
    }
}

@Composable
fun rememberUIController(
    router: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
    event: EventListener = EventListener(),
    context: Context = LocalContext.current
): UIController {
    val state = rememberModalBottomSheetState(
        initialValue = Hidden,
        confirmValueChange = {
            event.changeBottomSheet(it)
        }
    )
    return remember {
        UIController(
            router,
            state,
            scope,
            event,
            context
        )
    }
}