/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.trian.mvi.ui.internal.UIController
import app.trian.mvi.ui.internal.rememberUIController

@Composable
fun BaseMainApp(
    controller: UIController = rememberUIController(),
    dynamicColor: Boolean = true,
    lightColor: ColorScheme = lightColors,
    darkColor: ColorScheme = darkColors,
    typography: Typography = baseTypography,
    shapes: Shapes = baseShapes,
    content: @Composable (appState: UIController) -> Unit = { }
) {
    ApplicationTheme(
        dynamicColor = dynamicColor,
        lightColor = lightColor,
        darkColor = darkColor,
        typography = typography,
        shapes = shapes,
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            content.invoke(controller)
        }
    }
}