package app.trian.mvi.ui.internal

import kotlinx.coroutines.CoroutineStart
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class UIContract<State, Intent, Event>(
    val controller: UIController,
    val state: State,
    val intent: Intent? = null,
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
        start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend () -> Unit
    ) {
        controller.launch(context, start) { block() }
    }
    //end region

}