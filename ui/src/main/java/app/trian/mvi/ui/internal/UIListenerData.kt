/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.internal



class UIListenerData<State, Data, Event>(
    controller: UIController,
    state: State,
    val data: Data,
    mutation: (State) -> Unit = {},
    private val mutationData: (Data) -> Unit = {},
    dispatcher: (Event) -> Unit = {},
) : UIListener<State, Event>(
    controller = controller,
    state = state,
    mutation = mutation,
    dispatcher = dispatcher
) {
    fun commitData(d: Data.() -> Data) {
        this.mutationData(d(data))
    }
}
