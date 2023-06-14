/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui

import androidx.compose.runtime.Composable
import app.trian.core.ui.listener.BaseEventListener
import app.trian.core.ui.listener.EventListener

@JvmName("UIWrapperEvent")
@Composable
inline fun <State, Event, T : BaseEventListener> UIWrapper(
    uiEvent: UIListener<State, Event, T>,
    content: @Composable UIListener<State, Event, T>.() -> Unit
) {
    content(uiEvent)
}

@JvmName("UIWrapper")
@Composable
inline fun <State, Event> UIWrapper(
    uiEvent: UIListener<State, Event, EventListener>,
    content: @Composable UIListener<State, Event, EventListener>.() -> Unit
) {
    content(uiEvent)
}

@JvmName("UIWrapperDataEvent")
@Composable
inline fun <State, Data, Event, T : BaseEventListener> UiWrapperData(
    uiEvent: UIListenerData<State, Data, Event, T>,
    content: @Composable UIListenerData<State, Data, Event, T>.() -> Unit
) {
    content(uiEvent)
}

@JvmName("UIWrapper")
@Composable
inline fun <State, Data, Event> UiWrapperData(
    uiEvent: UIListenerData<State, Data, Event, EventListener>,
    content: @Composable UIListenerData<State, Data, Event, EventListener>.() -> Unit
) {
    content(uiEvent)
}
