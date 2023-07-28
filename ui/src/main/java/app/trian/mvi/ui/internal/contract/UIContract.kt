package app.trian.mvi.ui.internal.contract

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import app.trian.mvi.ui.internal.UIController
import kotlinx.coroutines.CoroutineStart
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class UIContract<State, Event>(
    val controller: UIController,
    val state: State,
    private val mutation: (State) -> Unit = {},
    private val dispatcher: (Event) -> Unit = {},
) {
    val navigator get() = controller.navigator
    val keyboard get() = controller.keyboard
    val toast get() = controller.toast
    fun commit(s: State.() -> State) {
        this.mutation(s(state))
    }

    fun dispatch(e: Event) {
        this.dispatcher(e)
    }

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend () -> Unit
    ) = controller.launch(context, start) { block() }


    //end region

    /**
     * for collecting side effect from viewModel
     * and `onDispose` require default effect
     * when screen destroyed or trigger side effect
     * */
    @Composable
    fun <T> UseEffect(
        key: T,
        onReset: State.() -> State,
        onEffect: suspend T.() -> Unit
    ) {
        LaunchedEffect(key1 = key, block = {
            onEffect(key).also {
                mutation.invoke(onReset(state))
            }
        })
        DisposableEffect(key1 = key, effect = {
            onDispose {
                mutation.invoke(onReset(state))
            }
        })
    }
}