/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui


import android.content.Context
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.core.ui.listener.BaseEventListener
import app.trian.core.ui.listener.EventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UIController(
    val router: NavHostController,
    val bottomSheetState: ModalBottomSheetState,
    val scope: CoroutineScope,
    val event: BaseEventListener = EventListener(),
    val context: Context
) {
    var currentRoute by mutableStateOf("")
    var snackbarHostState by mutableStateOf(
        SnackbarHostState()
    )

    fun showSnackbar(message: String) {
        runSuspend {
            snackbarHostState.showSnackbar(
                message,
                duration = SnackbarDuration.Short
            )
        }
    }

    fun showSnackbar(message: Int) {
        runSuspend {
            snackbarHostState.showSnackbar(
                context.getString(message),
                duration = SnackbarDuration.Short
            )
        }
    }

    fun showSnackbar(message: Int, vararg params: String) {
        runSuspend {
            snackbarHostState.showSnackbar(
                context.getString(message, *params),
                duration = SnackbarDuration.Short
            )
        }
    }

    fun showSnackbar(message: String, duration: SnackbarDuration) {
        runSuspend {
            snackbarHostState.showSnackbar(
                message,
                duration = duration
            )
        }
    }

    fun showSnackbar(message: String, actionLabel: String, onAction: () -> Unit = {}) {
        runSuspend {
            when (snackbarHostState.showSnackbar(
                message,
                actionLabel = actionLabel
            )) {
                SnackbarResult.Dismissed -> Unit
                SnackbarResult.ActionPerformed -> onAction()
            }
        }
    }

    fun runSuspend(
        block: suspend () -> Unit = {}
    ) = scope.launch {
        block()
    }


    //region route
    fun navigateBackAndClose() {
        router.popBackStack(
            currentRoute,
            inclusive = true
        )
        exit()
    }

    fun navigateUp() {
        router.navigateUp()
    }

    /**
     * Navigation into [routeName] as destination and keep the previous route
     *
     * @param routeName is a destination
     * @param args is arguments/parameter also support multiple type
     *
     * @throws IllegalArgumentException from navHostController
     */
    fun navigate(routeName: String, vararg args: String) {
        var buildRoute = routeName
        if (args.isNotEmpty()) {

            buildRoute = buildString {
                append(routeName)
                args.forEach {
                    append("/")
                    append(it)
                }
            }
        }
        this.router.navigate(buildRoute)
    }

    /**
     * Navigation into [routeName] as destination and keep the previous route
     * if same route exist on back stack, will be replace with [routeName]
     *
     * @param routeName is a destination
     * @param args is arguments/parameter also support multiple type
     *
     * @throws IllegalArgumentException from navHostController
     */
    fun navigateSingleTop(routeName: String, vararg args: String) {
        var buildRoute = routeName
        if (args.isNotEmpty()) {

            buildRoute = buildString {
                append(routeName)
                args.forEach {
                    append("/")
                    append(it)
                }
            }
        }
        this.router.navigate(buildRoute) {
            launchSingleTop = true
        }
    }

    fun navigateSingleTop(routeName: String) {
        this.router.navigate(routeName) {
            launchSingleTop = true
        }
    }

    /**
     * Navigation into [routeName] as destination, and pop all backstack before last route
     * if same route exist on back stack, will be replace with [routeName]
     *
     * @param routeName is a destination
     * @param args is arguments/parameter also support multiple type
     *
     * @throws IllegalArgumentException from navHostController
     */
    fun navigateAndReplace(routeName: String, vararg args: String) {
        var buildRoute = routeName
        if (args.isNotEmpty()) {
            buildRoute = buildString {
                append(routeName)
                args.forEach {
                    append("/")
                    append(it)
                }
            }
        }
        this.router.navigate(buildRoute) {
            popUpTo(router.graph.startDestinationId) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    /**
     * Navigation into [routeName] as destination, and pop all backstack from last route
     * if same route exist on back stack, will be replace with [routeName]
     *
     * @param routeName is a destination
     * @param args is arguments/parameter also support multiple type
     *
     *
     * @throws IllegalArgumentException from navHostController
     */
    fun navigateAndReplaceAll(routeName: String, vararg args: String) {
        var buildRoute = routeName
        if (args.isNotEmpty()) {
            buildRoute = buildString {
                append(routeName)
                args.forEach {
                    append("/")
                    append(it)
                }
            }
        }
        this.router.navigate(buildRoute) {
            popUpTo(router.graph.startDestinationId) {
                inclusive = true
            }
        }
    }
    //end region

    //region event
    fun exit() =
        event.sendEventToApp("EXIT")
//end region

    fun listenChanges() = this
        .router
        .addOnDestinationChangedListener { _, destination, _ ->
            currentRoute = destination.route.orEmpty()
        }

    fun showBottomSheet() {
        runSuspend { bottomSheetState.show() }
    }

    fun hideBottomSheet() {
        runSuspend { bottomSheetState.hide() }
    }

    //region string
    fun getString(res: Int): String =
        context.getString(res)

    fun getString(res: Int, vararg params: String): String =
        context.getString(res, *params)
    //end region
}

@Composable
fun rememberUIController(
    event: BaseEventListener = EventListener(),
    router: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
    allowHideBottomSheet: Boolean = true,
    context: Context = LocalContext.current
): UIController {
    val state = rememberModalBottomSheetState(
        initialValue = Hidden,
        confirmValueChange = {
            if (allowHideBottomSheet)
                true
            else
                event.changeBottomSheet(it)
        }
    )
    return remember {
        UIController(
            router,
            state,
            scope,
            event,
            context
        )
    }
}