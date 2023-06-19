package app.trian.mvi.ui.internal

open class UIListener<State, Event>(
    val controller: UIController,
    val state: State,
    private val mutation: (State) -> Unit = {},
    private val dispatcher: (Event) -> Unit = {},
) {
    val navigator get() = controller.navigator
    val backStackEntry get() = controller.navigator.currentBackStackEntry
    fun commit(s: State.() -> State) {
        this.mutation(s(state))
    }

    fun dispatch(e: Event) {
        this.dispatcher(e)
    }
    //end region

}