/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.trian.core.ui.listener.BaseEventListener
import app.trian.core.ui.listener.EventListener
import app.trian.core.ui.theme.ApplicationTheme

@Composable
@JvmName("BaseMainAppEvent")
inline fun <reified T:BaseEventListener> BaseMainApp(
    controller: UIController<T>,
    crossinline content: @Composable (appState: UIController<T>) -> Unit = { }
) {
    ApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            content.invoke(controller)
        }
    }
}

@Composable
@JvmName("BaseMainApp")
inline fun  BaseMainApp(
    controller: UIController<EventListener> = rememberUIController(),
    crossinline content: @Composable (appState: UIController<EventListener>) -> Unit = { }
) {
    ApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            content.invoke(controller)
        }
    }
}