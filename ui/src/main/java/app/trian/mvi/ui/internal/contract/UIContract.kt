package app.trian.mvi.ui.internal.contract

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import app.trian.mvi.ui.internal.UIController
import kotlinx.coroutines.CoroutineStart
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class UIContract<State:MviState<*>, Action>(
    val controller: UIController,
    val state: State,
    private val mutation: (State) -> Unit = {},
    private val dispatcher: (Action) -> Unit = {},
) {
    val navigator get() = controller.navigator
    val keyboard get() = controller.keyboard
    val toast get() = controller.toast
    /**
     * direct mutate state.
     * @param state state
     * */
    fun commit(state: State.() -> State) {
        this.mutation(state(this.state))
    }

    /**
     * emit Action to ViewModel.
     * @param action
     * */
    fun dispatch(action: Action) {
        this.dispatcher(action)
    }

    /**
     * Run suspend function from coroutine scope
     * @param context CoroutineContext
     * @param start
     * @param block invoke
     * */
    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend () -> Unit
    ) = controller.launch(context, start) { block() }


    //end region

    /**
     * Collecting side effect from viewModel.
     * @param commit for mutate state effect to default
     * @param onEffect when effect emitted from `viewModel`
     * */
    @Composable
    fun <T> UseEffect(
        commit: State.() -> State,
        onEffect: suspend T.() -> Unit
    ) {
        LaunchedEffect(key1 = state.effect, block = {
            onEffect(state.effect as T).also {

                mutation.invoke(commit(state))
            }
        })
        DisposableEffect(key1 = state.effect, effect = {
            onDispose {
                mutation.invoke(commit(state))
            }
        })
    }
}