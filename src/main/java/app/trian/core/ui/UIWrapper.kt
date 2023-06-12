/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui

import androidx.compose.runtime.Composable

@Composable
inline fun <State, Event> UIWrapper(
    uiEvent: UIListener<State, Event>,
    content: @Composable UIListener<State, Event>.() -> Unit
) { content(uiEvent) }

@Composable
inline fun <State, Data, Event> UiWrapperData(
    uiEvent: UIListenerData<State, Data, Event>,
    content: @Composable UIListenerData<State, Data, Event>.() -> Unit
) { content(uiEvent) }
