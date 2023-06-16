/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui

import android.widget.Toast
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.trian.core.ui.extensions.hideKeyboard
import app.trian.core.ui.listener.KeyboardListener
import app.trian.core.ui.listener.NavigationListener
import app.trian.core.ui.listener.SnacbarListener
import app.trian.core.ui.listener.ToastListener
import app.trian.core.ui.viewModel.BaseViewModel


inline fun <reified ViewModel : BaseViewModel<*, *>> NavGraphBuilder.pageWrapper(
    route: String,
    controller: UIController,
    parent: String? = null,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
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
            val parentEntry = remember(controller.router.currentBackStackEntry) {
                controller
                    .router
                    .getBackStackEntry(parent)
            }
            hiltViewModel(parentEntry)
        })
        LaunchedEffect(key1 = viewModel, block = {
            viewModel.addNavigationListener(
                object : NavigationListener {
                    override fun navigateUp() {
                        controller.navigateUp()
                    }

                    override fun navigate(route: String, vararg params: String) {
                        controller.navigate(route, *params)
                    }

                    override fun navigateSingleTop(route: String, vararg params: String) {
                        controller.navigateSingleTop(route, *params)
                    }

                    override fun navigateAndReplace(route: String, vararg params: String) {
                        controller.navigateAndReplace(route, *params)
                    }

                    override fun navigateBackAndClose(route: String, vararg params: String) {
                        controller.navigateBackAndClose()
                    }
                }
            )
            viewModel.addToastListener(
                object : ToastListener {
                    override fun showToast(message: String, length: Int) {
                        Toast.makeText(controller.context, message, length).show()
                    }

                    override fun showToast(message: Int, vararg params: String, length: Int) {
                        Toast.makeText(
                            controller.context,
                            controller.context.getString(message.toInt(), *params),
                            length
                        ).show()
                    }

                }
            )

            viewModel.addBottomSheetListener { isShow ->
                if (isShow) controller.showBottomSheet()
                else controller.hideBottomSheet()
            }

            viewModel.addSnackbarListener(object : SnacbarListener {
                override fun showSnackbar(message: String) {
                    controller.showSnackbar(message)
                }

                override fun showSnackbar(message: String, duration: SnackbarDuration) {
                    controller.showSnackbar(message, duration)
                }

                override fun showSnackbar(id: Int, vararg params: String) {
                    controller.showSnackbar(id, *params)
                }

                override fun showSnackbar(
                    id: Int,
                    vararg params: String,
                    snackbarDuration: SnackbarDuration
                ) {
                    controller.showSnackbar(
                        controller.context.getString(id, *params),
                        snackbarDuration
                    )
                }

            })

            viewModel.addOnKeyboardListener(object : KeyboardListener {
                override fun onShowKeyboard() {

                }

                override fun onHideKeyboard() {
                    controller.context.hideKeyboard()
                }

            })
        })
        content(viewModel)
    }
}
