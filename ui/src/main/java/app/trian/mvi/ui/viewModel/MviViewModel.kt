/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.viewModel

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.mvi.ui.ResultState
import app.trian.mvi.ui.ResultStateData
import app.trian.mvi.ui.ResultStateWithProgress
import app.trian.mvi.ui.internal.UIController
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

abstract class MviViewModel<State : Parcelable, Action>(
    private val initialState: State,
) : ViewModel() {
    companion object {
        val dispatcher: CoroutineDispatcher = Dispatchers.Default
    }

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState get() = _uiState.asStateFlow()

    private val action = Channel<Action>(Channel.UNLIMITED)
    private lateinit var _controller: UIController
    val controller get() = _controller


    //listener
    fun setController(controller: UIController) {
        _controller = controller
    }
    //

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

    protected inline fun <reified T> Flow<ResultState<T>>.onEach(
        crossinline loading: () -> Unit = {},
        crossinline error: (String) -> Unit = {},
        crossinline success: (T) -> Unit = {}
    ) = async {
        this.catch { error(it.message.orEmpty()) }
            .collect {
                when (it) {
                    is ResultState.Error -> error(
                        it.message.ifEmpty {
                            try {
                                controller.getString(it.stringId)
                            }catch (e:Exception){
                                "Unknown error message"
                            }

                        }
                    )

                    ResultState.Loading -> loading()
                    is ResultState.Result -> success(it.data)
                }
            }
    }

    protected inline fun <reified T> Flow<ResultStateData<T>>.onEach(
        crossinline loading: () -> Unit = {},
        crossinline error: (String) -> Unit = {},
        crossinline success: (T) -> Unit = {},
        crossinline empty: () -> Unit = {}
    ) = async {
        this.catch { error(it.message.orEmpty()) }
            .collect {
                when (it) {
                    is ResultStateData.Error -> error(
                        it.message.ifEmpty {
                            try {
                                controller.getString(it.stringId)
                            }catch (e:Exception){
                                "Unknown error message"
                            }
                        }
                    )

                    ResultStateData.Loading -> loading()
                    is ResultStateData.Result -> success(it.data)
                    ResultStateData.Empty -> empty()
                }
            }
    }

    protected inline fun <reified T> Flow<ResultStateWithProgress<T>>.onEach(
        crossinline loading: () -> Unit = {},
        crossinline error: (String) -> Unit = {},
        crossinline onFinish: (T) -> Unit = {},
        crossinline onProgress: (progress: Int) -> Unit = {}
    ) = async {
        this.catch { error(it.message.orEmpty()) }
            .collect {
                when (it) {
                    is ResultStateWithProgress.Error -> error(
                        it.message.ifEmpty {
                            controller.getString(it.stringId)
                        }
                    )

                    ResultStateWithProgress.Loading -> loading()
                    is ResultStateWithProgress.Finish -> onFinish(it.data)
                    is ResultStateWithProgress.Progress -> onProgress(it.progress)
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

abstract class MviViewModelData<State : Parcelable, DataState : Parcelable, Action>(
    initialState: State,
    private val initialData: DataState
) : MviViewModel<State, Action>(initialState) {
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
