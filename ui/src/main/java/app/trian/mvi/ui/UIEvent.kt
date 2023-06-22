package app.trian.mvi.ui

sealed interface UIEvent {
    class ShowToast(val message: String) : UIEvent

    class ShowSnackBar(val message: String) : UIEvent
    class NavigateAndReplace(val route: String, vararg val params: String) : UIEvent
    class NavigateSingleTop(val route: String, vararg val params: String) : UIEvent
    class Navigate(val route: String, vararg val params: String) : UIEvent
    object NavigateBackAndClose : UIEvent
    object NavigateUp : UIEvent
    object Nothing : UIEvent
}