/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.core.ui.ResultState
import app.trian.core.ui.listener.BottomSheetListener
import app.trian.core.ui.listener.BottomSheetListenerImpl
import app.trian.core.ui.listener.KeyboardListener
import app.trian.core.ui.listener.KeyboardListenerImpl
import app.trian.core.ui.listener.NavigationListener
import app.trian.core.ui.listener.NavigationListenerImpl
import app.trian.core.ui.listener.SnacbarListener
import app.trian.core.ui.listener.SnackbarListenerImpl
import app.trian.core.ui.listener.ToastListener
import app.trian.core.ui.listener.ToastListenerImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
abstract class BaseViewModel<State : Parcelable, Action>(
    val context: Context,
    private val initialState: State,
) : ViewModel() {
    companion object {
        val dispatcher: CoroutineDispatcher = Dispatchers.Default
    }

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState get() = _uiState.asStateFlow()

    private val action = Channel<Action>(Channel.UNLIMITED)

    private var _toastListener: ToastListener = ToastListenerImpl()
    protected val toast get() = _toastListener
    private var _navigationListener: NavigationListener = NavigationListenerImpl()
    protected val navigation get() = _navigationListener

    private var _bottomSheetListener: BottomSheetListener = BottomSheetListenerImpl()
    protected val bottomSheet get() = _bottomSheetListener

    private var _snackbarListener: SnacbarListener = SnackbarListenerImpl()
    protected val snackbar get() = _snackbarListener

    private var _keyboardListener: KeyboardListener = KeyboardListenerImpl()
    protected val keyboard get() = _keyboardListener


    //listener
    fun addToastListener(listener: ToastListener) {
        _toastListener = listener
    }

    fun addNavigationListener(listener: NavigationListener) {
        _navigationListener = listener
    }

    fun addBottomSheetListener(listener: BottomSheetListener) {
        _bottomSheetListener = listener
    }

    fun addSnackbarListener(listener: SnacbarListener) {
        _snackbarListener = listener
    }

    fun addOnKeyboardListener(listener: KeyboardListener) {
        _keyboardListener = listener
    }

    fun showKeyboard() {
        _keyboardListener.onShowKeyboard()
    }

    fun hideKeyboard() {
        _keyboardListener.onHideKeyboard()
    }

    //end
    protected fun onEvent(
        block: suspend (Action) -> Unit
    ) {
        async {
            action
                .consumeAsFlow()
                .collect {
                    block(it)
                }
        }
    }

    protected inline fun async(crossinline block: suspend () -> Unit) = with(viewModelScope) {
        launch { block() }
    }

    protected inline fun asyncWithState(crossinline block: suspend State.() -> Unit) =
        async { block(uiState.value) }

    protected suspend inline fun <T> await(crossinline block: suspend () -> T): T =
        withContext(dispatcher) { block() }

    protected inline fun asyncFlow(crossinline block: suspend () -> Unit) =
        async { withContext(dispatcher) { block() } }

    protected abstract fun handleActions()
    fun commit(state: State) {
        _uiState.tryEmit(state)
    }

    fun commit(state: State.() -> State) {
        _uiState.tryEmit(state(uiState.value))
    }

    protected infix fun BaseViewModel<State, Action>.commit(s: State.() -> State) {
        commit(s(uiState.value))
    }

    protected inline fun <reified T> Flow<ResultState<T>>.onEach(
        crossinline success: (T) -> Unit = {},
        crossinline loading: () -> Unit = {},
        crossinline error: (String) -> Unit = {}
    ) = async {
        this.catch { error(it.message.orEmpty()) }
            .collect {
                when (it) {
                    is ResultState.Error -> error(
                        it.message.ifEmpty {
                            context.getString(it.stringId)
                        }
                    )
                    ResultState.Loading -> loading()
                    is ResultState.Result -> success(it.data)
                }
            }
    }

    fun resetState() {
        commit(initialState)
    }

    fun dispatch(e: Action) = async { action.send(e) }


    override fun onCleared() {
        super.onCleared()

        action.cancel()
        _uiState.tryEmit(initialState)
        async { currentCoroutineContext().cancel() }
    }
}

abstract class BaseViewModelData<State : Parcelable, DataState : Parcelable, Action>(
    context: Context,
    initialState: State,
    private val initialData: DataState
) : BaseViewModel<State, Action>(context, initialState) {
    private val _uiDataState: MutableStateFlow<DataState> = MutableStateFlow(initialData)
    val uiDataState get() = _uiDataState.asStateFlow()

    protected inline fun asyncWithData(crossinline block: suspend DataState.() -> Unit) =
        async { block(uiDataState.value) }

    fun commitData(dataState: DataState) {
        _uiDataState.tryEmit(dataState)
    }

    fun commitData(dataState: DataState.() -> DataState) {
        commitData(dataState(uiDataState.value))
    }

    fun resetDataState() {
        commitData(initialData)
    }


    override fun onCleared() {
        super.onCleared()
        _uiDataState.tryEmit(initialData)
    }
}
