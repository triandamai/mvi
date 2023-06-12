/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui

import app.trian.core.ui.extensions.addOnBottomSheetStateChangeListener
import app.trian.core.ui.extensions.navigateBackAndClose
import app.trian.core.ui.extensions.hideBottomSheet
import app.trian.core.ui.extensions.hideKeyboard
import app.trian.core.ui.extensions.navigate
import app.trian.core.ui.extensions.navigateAndReplace
import app.trian.core.ui.extensions.navigateAndReplaceAll
import app.trian.core.ui.extensions.navigateSingleTop
import app.trian.core.ui.extensions.navigateUp
import app.trian.core.ui.extensions.showBottomSheet
import app.trian.core.ui.listener.BottomSheetChangeListener

open class UIListener<State, Event>(
    val controller: UIController,
    val state: State,
    private val commit: (State) -> Unit = {},
    private val dispatcher: (Event) -> Unit = {},
) {
    fun commit(s: State.() -> State) {
        this.commit(s(state))
    }

    fun dispatch(e: Event) {
        this.dispatcher(e)
    }

    //application state nav
    //region navigation
    fun backAndClose() =
        controller.navigateBackAndClose()

    fun navigateUp() =
        controller.navigateUp()

    fun navigate(routeName: String, vararg args: String) =
        controller.navigate(routeName, *args)

    fun navigateSingleTop(routeName: String, vararg args: String) =
        controller.navigateSingleTop(routeName, *args)

    fun navigateSingleTop(routeName: String) =
        controller.navigateSingleTop(routeName)

    fun navigateAndReplace(routeName: String, vararg args: String) =
        controller.navigateAndReplace(routeName, *args)

    fun navigateAndReplaceAll(routeName: String, vararg args: String) =
        controller.navigateAndReplaceAll(routeName, *args)

    fun navigateAndReplaceAll(routeName: String) =
        controller.navigateAndReplaceAll(routeName)

    //end region
    //region bottomsheet
    fun hideBottomSheet() =
        controller.hideBottomSheet()

    fun showBottomSheet() =
        controller.showBottomSheet()

    fun addOnBottomSheetStateChangeListener(listener: BottomSheetChangeListener) =
        controller.addOnBottomSheetStateChangeListener(listener)
    //end region

    //keyboard
    fun hideKeyboard() =
        controller.context.hideKeyboard()
    //end region

    //snackbar
    //region snakcbar

    fun showSnackbar(message: String) =
        controller.showSnackbar(message)
    fun showSnackbar(message: Int) =
        controller.showSnackbar(message)
    fun showSnackbar(message: Int, vararg params: String) =
        controller.showSnackbar(message, *params)
    //

    //end region
    //end
}
class UIListenerData<State, Data, Event>(
    controller: UIController,
    state: State,
    val data: Data,
    commit: (State) -> Unit = {},
    private val commitData: (Data) -> Unit = {},
    dispatcher: (Event) -> Unit = {},
) : UIListener<State, Event>(
    controller = controller,
    state = state,
    commit = commit,
    dispatcher = dispatcher
) {
    fun commitData(d: Data.() -> Data) {
        this.commitData(d(data))
    }
}
