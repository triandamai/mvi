/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.resepku.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
inline fun <reified VM : BaseViewModel<*, *>> UIWrapper(
    appState: ApplicationState,
    parent: String? = null,
    content: @Composable VM.() -> Unit = {}
) {
    val vm = (if (parent.isNullOrEmpty()) {
        hiltViewModel()
    } else {
        val parentEntry = remember(appState.router.currentBackStackEntry) {
            appState
                .router
                .getBackStackEntry(parent)
        }
        hiltViewModel<VM>(parentEntry)
    }).also { it.setAppState(appState) }
    content(vm)
}

@Composable
inline fun BaseScreen(
    appState: ApplicationState = rememberApplicationState(),
    backgroundColor:Color = MaterialTheme.colorScheme.surface,
    noinline topAppBar: @Composable () -> Unit = {},
    noinline bottomAppBar: @Composable () -> Unit = {},
    crossinline bottomSheet: @Composable ColumnScope.() -> Unit = {},
    crossinline content: @Composable (PaddingValues) -> Unit = {}
) {
    ModalBottomSheetLayout(
        sheetState = appState.bottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(
                        minHeight = 100.dp
                    )
            ) {
                bottomSheet(this@Column)
            }
        },
        content = {
            Scaffold(
                topBar = topAppBar,
                bottomBar = bottomAppBar,
                containerColor = backgroundColor
            ) {
                content(it)
            }
        },
    )
}