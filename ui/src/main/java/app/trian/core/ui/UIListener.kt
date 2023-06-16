/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui

import app.trian.core.ui.extensions.hideKeyboard
import app.trian.core.ui.listener.BottomSheetChangeListener

open class UIListener<State, Event>(
    val controller: UIController,
    val state: State,
    private val commit: (State) -> Unit = {},
    private val dispatcher: (Event) -> Unit = {},
) {
    val router get() = controller
    val backStackEntry get() = controller.router.currentBackStackEntry
    fun commit(s: State.() -> State) {
        this.commit(s(state))
    }

    fun dispatch(e: Event) {
        this.dispatcher(e)
    }

    //end region
    //region bottomsheet
    fun hideBottomSheet() =
        controller.hideBottomSheet()

    fun showBottomSheet() =
        controller.showBottomSheet()
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
