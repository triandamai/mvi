/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.mvi.ui.internal.contract.MviState
import app.trian.mvi.ui.internal.listener.NavigationListener
import app.trian.mvi.ui.internal.listener.ToastListener
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<State : MviState<*>, Action>(
    private val initialState: State,
) : ViewModel(), NavigationListener {

    private var navigationListener: NavigationListener? = null
    private var toastListener: ToastListener? = null

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState get() = _uiState.asStateFlow()
    protected abstract fun onAction(action: Action)

    //toast
    fun addOnToastListener(toastListener: ToastListener) {
        this.toastListener = toastListener
    }

    protected fun showToast(message: String, length: Int) {
        this.toastListener?.show(message, length)
    }
    //

    // navigation
    fun addOnNavigationListener(navigationListener: NavigationListener) {
        this.navigationListener = navigationListener
    }

    override fun navigateUp() {
        this.navigationListener?.navigateUp()
    }

    override fun navigateBack() {
        this.navigationListener?.navigateBack()
    }

    override fun navigate(routeName: String, vararg params: String) {
        this.navigationListener?.navigate(routeName, *params)
    }

    override fun navigateSingleTop(routeName: String, vararg params: String) {
        this.navigationListener?.navigateSingleTop(routeName, *params)
    }

    override fun navigateAndReplace(routeName: String, vararg params: String) {
        this.navigationListener?.navigateAndReplace(routeName, *params)
    }

    override fun navigateBackAndClose() {
        this.navigationListener?.navigateBackAndClose()
    }
    //

    //end
    protected inline fun async(crossinline block: suspend () -> Unit) = with(viewModelScope) {
        launch { block() }
    }

    protected inline fun asyncWithState(crossinline block: suspend State.() -> Unit) =
        async { block(uiState.value) }

    fun commit(state: State) {
        _uiState.tryEmit(state)
    }

    fun commit(state: State.() -> State) {
        _uiState.tryEmit(state(uiState.value))
    }

    fun dispatch(action: Action) = onAction(action)

    fun resetState() {
        commit(initialState)
    }

    override fun onCleared() {
        super.onCleared()
        _uiState.tryEmit(initialState)
        async { currentCoroutineContext().cancel() }
    }
}