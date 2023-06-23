/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.viewModel

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.trian.mvi.ui.UIEvent
import app.trian.mvi.ui.ResultState
import app.trian.mvi.ui.ResultStateData
import app.trian.mvi.ui.ResultStateWithProgress
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<State : Parcelable, Intent, Action>(
    private val initialState: State,
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState get() = _uiState.asStateFlow()

    private val _uiEventChannel = Channel<UIEvent>(Channel.BUFFERED)
    val uiEvent = _uiEventChannel.receiveAsFlow()

    private val _intent:MutableStateFlow<Intent?> = MutableStateFlow(null)
    val intent get() = _intent.asStateFlow()

    protected abstract fun onAction(action: Action)

    //end
    protected inline fun async(crossinline block: suspend () -> Unit) = with(viewModelScope) {
        launch { block() }
    }

    protected inline fun asyncWithState(crossinline block: suspend State.() -> Unit) =
        async { block(uiState.value) }

    protected inline fun <reified T> Flow<ResultState<T>>.onEach(
        crossinline loading: () -> Unit = {},
        crossinline error: (String, stringId: Int) -> Unit = { _, _ -> },
        crossinline success: (T) -> Unit = {}
    ) = async {
        this.catch { error(it.message.orEmpty()) }
            .collect {
                when (it) {
                    is ResultState.Error -> error(it.message, it.stringId)

                    ResultState.Loading -> loading()
                    is ResultState.Result -> success(it.data)
                }
            }
    }

    protected inline fun <reified T> Flow<ResultStateData<T>>.onEach(
        crossinline loading: () -> Unit = {},
        crossinline error: (String, stringId: Int) -> Unit = { _, _ -> },
        crossinline success: (T) -> Unit = {},
        crossinline empty: () -> Unit = {}
    ) = async {
        this.catch { error(it.message.orEmpty()) }
            .collect {
                when (it) {
                    is ResultStateData.Error -> error(it.message, it.stringId)
                    ResultStateData.Loading -> loading()
                    is ResultStateData.Result -> success(it.data)
                    ResultStateData.Empty -> empty()
                }
            }
    }

    protected inline fun <reified T> Flow<ResultStateWithProgress<T>>.onEach(
        crossinline loading: () -> Unit = {},
        crossinline error: (String, stringId: Int) -> Unit = { _, _ -> },
        crossinline onFinish: (T) -> Unit = {},
        crossinline onProgress: (progress: Int) -> Unit = {}
    ) = async {
        this.catch { error(it.message.orEmpty()) }
            .collect {
                when (it) {
                    is ResultStateWithProgress.Error -> error(it.message, it.stringId)
                    ResultStateWithProgress.Loading -> loading()
                    is ResultStateWithProgress.Finish -> onFinish(it.data)
                    is ResultStateWithProgress.Progress -> onProgress(it.progress)
                }
            }
    }


    fun commit(state: State) {
        _uiState.tryEmit(state)
    }

    fun commit(state: State.() -> State) {
        _uiState.tryEmit(state(uiState.value))
    }

    fun dispatch(action: Action) = onAction(action)

    fun sendIntent(intent: Intent) {
        _intent.tryEmit(intent)
    }

    fun sendUiEvent(event: UIEvent) {
        _uiEventChannel.trySend(event)
    }

    fun resetState() {
        commit(initialState)
    }

    override fun onCleared() {
        super.onCleared()

        _uiEventChannel.trySend(UIEvent.Nothing)
        _uiState.tryEmit(initialState)
        async { currentCoroutineContext().cancel() }
    }
}