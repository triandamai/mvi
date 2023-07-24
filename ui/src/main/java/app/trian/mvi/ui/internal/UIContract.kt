package app.trian.mvi.ui.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
    ) {
        controller.launch(context, start) { block() }
    }

    //end region

    /**
     * for collecting side effect from viewModel
     * and `onDispose` require default effect
     * when screen destroyed or trigger side effect
     * */
    @Composable
    fun <T> UseEffect(
        key: T,
        onDispose: State.() -> State,
        block: suspend T.() -> Unit
    ) {
        fun reset() {
            mutation.invoke(onDispose(state))
        }
        LaunchedEffect(key1 = key, block = {
            block(key).also {
                reset()
            }
        })
        DisposableEffect(key1 = key, effect = {
            onDispose {
                reset()
            }
        })


    }

}