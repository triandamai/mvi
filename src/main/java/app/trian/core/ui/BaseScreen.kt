/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.trian.core.ui.listener.BaseEventListener
import app.trian.core.ui.listener.EventListener


@Composable
@JvmName("BaseScreenEvent")
inline fun <reified T : BaseEventListener> BaseScreen(
    controller: UIController<T>,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    noinline topAppBar: @Composable () -> Unit = {},
    noinline bottomBar: @Composable () -> Unit = {},
    crossinline bottomSheet: @Composable () -> Unit = { },
    crossinline content: @Composable (controller: UIController<T>) -> Unit = { }
) {
    ModalBottomSheetLayout(
        sheetContent = {
            Column(
                Modifier
                    .defaultMinSize(
                        minHeight = 50.dp
                    )
                    .wrapContentHeight()
            ) {
                bottomSheet()
            }
        },
        sheetState = controller.bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetShape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp
        )
    ) {
        Scaffold(
            topBar = topAppBar,
            bottomBar = bottomBar,
            snackbarHost = {
                SnackbarHost(
                    hostState = controller.snackbarHostState,
                    snackbar = {
                        Snackbar(snackbarData = it)
                    })
            },
            containerColor = backgroundColor,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                content(controller)
            }
        }
    }
}

@Composable
@JvmName("BaseScreen")
fun BaseScreen(
    controller: UIController<EventListener> = rememberUIController(),
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    topAppBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    bottomSheet: @Composable () -> Unit = { },
    content: @Composable (controller: UIController<EventListener>) -> Unit = { }
) {
    BaseScreen<EventListener>(
        controller = controller,
        backgroundColor = backgroundColor,
        topAppBar = topAppBar,
        bottomBar = bottomBar,
        bottomSheet = bottomSheet,
        content = content
    )
}