/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui

import androidx.compose.runtime.Composable

@Composable
inline fun <reified T : UIListener<*, *>> UIWrapper(
    uiEvent: T,
    content: @Composable T.() -> Unit
) { content(uiEvent) }