/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui

import androidx.compose.runtime.Composable
import app.trian.mvi.ui.internal.UIContract

@Composable
inline fun <reified T : UIContract<*,*, *>> UIWrapper(
    uiContract: T,
    content: @Composable T.() -> Unit
) { content(uiContract) }