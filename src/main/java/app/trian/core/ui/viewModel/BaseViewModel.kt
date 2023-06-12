/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.core.ui.Response
import app.trian.core.ui.UIController
import app.trian.core.ui.listener.BottomSheetListener
import app.trian.core.ui.listener.NavigationListener
import app.trian.core.ui.listener.ToastListener
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

    private lateinit var _toastListener: ToastListener
    protected val toast get() = _toastListener
    private lateinit var _navigationListener: NavigationListener
    protected val navigation get() = _navigationListener

    private lateinit var _bottomSheetListener: BottomSheetListener
    protected val bottomSheet get() = _bottomSheetListener


    //listener
    fun addToastListener(listener: ToastListener) {
        _toastListener = listener
    }

    fun addNavigationListener(listener: NavigationListener) {
        _navigationListener = listener
    }
    fun addBottomSheetListener(listener: BottomSheetListener){
        _bottomSheetListener = listener
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

    fun <T> Flow<Response<T>>.onEach(
        success: (T) -> Unit = {},
        loading: () -> Unit = {},
        error: (String) -> Unit = {}
    ) = async {
        this.catch { error(it.message.orEmpty()) }
            .collect {
                when (it) {
                    is Response.Error -> error(
                        it.message.ifEmpty {}
                    )

                    Response.Loading -> loading()
                    is Response.Result -> success(it.data)
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
