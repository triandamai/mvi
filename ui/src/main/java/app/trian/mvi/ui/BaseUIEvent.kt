package app.trian.mvi.ui

sealed interface BaseUIEvent {
    class ShowToast(val message: String) : BaseUIEvent

    class ShowSnackBar(val message: String) : BaseUIEvent
    class NavigateAndReplace(val route: String, vararg val params: String) : BaseUIEvent
    class NavigateSingleTop(val route: String, vararg val params: String) : BaseUIEvent
    class Navigate(val route: String, vararg val params: String) : BaseUIEvent
    object NavigateBackAndClose : BaseUIEvent
    object NavigateUp : BaseUIEvent
    object Nothing : BaseUIEvent
}