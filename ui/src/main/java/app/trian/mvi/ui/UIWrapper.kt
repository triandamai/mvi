/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui

import androidx.compose.runtime.Composable
import app.trian.mvi.ui.internal.UIListener

@Composable
inline fun <reified T : UIListener<*, *>> UIWrapper(
    uiEvent: T,
    content: @Composable T.() -> Unit
) { content(uiEvent) }