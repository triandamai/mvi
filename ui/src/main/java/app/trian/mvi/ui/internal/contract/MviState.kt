package app.trian.mvi.ui.internal.contract

abstract class MviState<out T> {
    abstract val effect:T
}