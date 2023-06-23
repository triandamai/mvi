/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.internal


import android.content.Context
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.mvi.ui.internal.listener.BaseEventListener
import app.trian.mvi.ui.internal.listener.EventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class UIController(
    private val router: NavHostController,
    private val scope: CoroutineScope,
    private val event: BaseEventListener = EventListener(),
    private val context: Context
) {
    val navigator get() = Navigator(router, event)
    val keyboard get() = Keyboard(context)
    val toast get() = ToastMvi(context)

    //region event

    //coroutine
    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend () -> Unit
    ) {
        scope.launch(context, start) { block() }
    }

    //end
    //region string
    fun getString(res: Int): String =
        context.getString(res)

    fun getString(res: Int, vararg params: String): String =
        context.getString(res, *params)
    //end region
}

@Composable
fun rememberUIController(
    event: BaseEventListener = EventListener(),
    router: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current
): UIController {

    return remember {
        UIController(
            router,
            scope,
            event,
            context
        )

    }
}