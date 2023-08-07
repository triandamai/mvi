/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.trian.mvi.ui.internal.UIController
import app.trian.mvi.ui.internal.listener.NavigationListener
import app.trian.mvi.ui.viewModel.MviViewModel


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
        LaunchedEffect(key1 = viewModel, block = {
            viewModel.addOnNavigationListener(object : NavigationListener {
                override fun navigateUp() {
                    controller.navigator.navigateUp()
                }

                override fun navigateBack() {
                    controller.navigator.navigateBack()
                }

                override fun navigate(routeName: String, vararg params: String) {
                    controller.navigator.navigate(
                        routeName,
                        *params
                    )
                }

                override fun navigateSingleTop(routeName: String, vararg params: String) {
                    controller.navigator.navigateSingleTop(
                        routeName,
                        *params
                    )
                }

                override fun navigateAndReplace(routeName: String, vararg params: String) {
                    controller.navigator.navigateAndReplace(routeName, *params)
                }

                override fun navigateBackAndClose() {
                    controller.navigator.navigateBackAndClose()
                }

            })
        })

        content(viewModel)
    }
}

/**
 * Implementation without viewModel
 * */
inline fun NavGraphBuilder.pageWrapper(
    route: String,
    arguments: List<NamedNavArgument> = listOf(),
    deepLinks: List<NavDeepLink> = listOf(),
    crossinline content: @Composable () -> Unit = {}
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks
    ) {
        content()
    }
}

