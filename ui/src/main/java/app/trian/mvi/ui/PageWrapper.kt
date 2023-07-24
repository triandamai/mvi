/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.trian.mvi.ui.internal.UIController
import app.trian.mvi.ui.viewModel.MviViewModel
import kotlinx.coroutines.flow.catch


inline fun <reified ViewModel : MviViewModel<*, *>> NavGraphBuilder.pageWrapper(
    route: String,
    controller: UIController,
    parent: String? = null,
    arguments: List<NamedNavArgument> = listOf(),
    deepLinks: List<NavDeepLink> = listOf(),
    crossinline content: @Composable ViewModel.() -> Unit = {}
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks
    ) {
        val ctx = LocalContext.current
        val viewModel: ViewModel = (if (parent.isNullOrEmpty()) {
            hiltViewModel()
        } else {
            val parentEntry = remember(controller.navigator.currentBackStackEntry) {
                controller
                    .navigator
                    .getBackStackEntry(parent)
            }
            hiltViewModel(parentEntry)
        })
        LaunchedEffect(key1 = Unit, block = {
            viewModel.uiEvent
                .catch {

                }.collect { event ->
                    when (event) {
                        is UIEvent.ShowToast -> Toast.makeText(
                            ctx,
                            event.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        is UIEvent.ShowSnackBar -> {}
                        is UIEvent.NavigateAndReplace ->
                            controller.navigator.navigateAndReplace(event.route, *event.params)

                        is UIEvent.NavigateSingleTop -> controller.navigator.navigateSingleTop(
                            event.route,
                            *event.params
                        )

                        is UIEvent.Navigate -> controller.navigator.navigate(
                            event.route,
                            *event.params
                        )

                        UIEvent.NavigateBackAndClose -> controller.navigator.navigateBackAndClose()
                        UIEvent.NavigateUp -> controller.navigator.navigateUp()
                        UIEvent.Nothing -> {}

                    }
                }
        })

        content(viewModel)
    }
}

