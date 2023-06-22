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


inline fun <reified ViewModel : MviViewModel<*, *, *>> NavGraphBuilder.pageWrapper(
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
            viewModel.uiEvent.catch { }.collect { event ->
                when (event) {
                    is BaseUIEvent.ShowToast -> Toast.makeText(
                        ctx,
                        event.message,
                        Toast.LENGTH_SHORT
                    )
                        .show()

                    is BaseUIEvent.ShowSnackBar -> {

                    }

                    is BaseUIEvent.NavigateAndReplace ->
                        controller.navigator.navigateAndReplace(event.route, *event.params)

                    is BaseUIEvent.NavigateSingleTop -> controller.navigator.navigateSingleTop(
                        event.route,
                        *event.params
                    )

                    BaseUIEvent.NavigateBackAndClose -> controller.navigator.navigateBackAndClose()
                    BaseUIEvent.NavigateUp -> controller.navigator.navigateUp()
                    BaseUIEvent.Nothing -> {}
                    is BaseUIEvent.Navigate -> controller.navigator.navigateSingleTop(
                        event.route,
                        *event.params
                    )
                }
            }
        })

        content(viewModel)
    }
}

