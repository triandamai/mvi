package app.trian.mvi.ui.internal

import androidx.navigation.NavHostController
import app.trian.mvi.ui.internal.listener.BaseEventListener
import app.trian.mvi.ui.internal.listener.NavigationListener

class Navigator(
    private val navHostController: NavHostController,
    private val event: BaseEventListener
) : NavigationListener {
    val navHost get() = navHostController
    val arguments get() = navHostController.currentBackStackEntry?.arguments
    val currentBackStackEntry get() = navHostController.currentBackStackEntry
    fun getBackStackEntry(route: String) = navHostController.getBackStackEntry(route)

    //region route

    /**
     * Navigate back
     * @throws IllegalArgumentException from navHostController
     */
    override fun navigateUp() {
        navHostController.navigateUp()
    }

    /**
     * Navigation into [routeName] as destination and keep the previous route
     *
     * @param routeName is a destination
     * @param args is arguments/parameter also support multiple type
     *
     * @throws IllegalArgumentException from navHostController
     */
    override fun navigate(routeName: String, vararg params: String) {
        var buildRoute = routeName
        if (params.isNotEmpty()) {
            buildRoute = buildString {
                append(routeName)
                params.forEach {
                    append("/")
                    append(it)
                }
            }
        }
        this.navHostController.navigate(buildRoute)
    }

    /**
     * Navigation into [routeName] as destination and keep the previous route
     * if same route exist on back stack, will be replace with [routeName]
     *
     * @param routeName is a destination
     * @param params is arguments/parameter also support multiple type
     *
     * @throws IllegalArgumentException from navHostController
     */
    override fun navigateSingleTop(routeName: String, vararg params: String) {
        var buildRoute = routeName
        if (params.isNotEmpty()) {
            buildRoute = buildString {
                append(routeName)
                params.forEach {
                    append("/")
                    append(it)
                }
            }
        }
        this.navHostController.navigate(buildRoute) {
            launchSingleTop = true
        }
    }
    /**
     * Navigation into [routeName] as destination, and pop all backstack before last route
     * if same route exist on back stack, will be replace with [routeName]
     *
     * @param routeName is a destination
     * @param params is arguments/parameter also support multiple type
     *
     * @throws IllegalArgumentException from navHostController
     */
    override fun navigateAndReplace(routeName: String, vararg params: String) {
        var buildRoute = routeName
        if (params.isNotEmpty()) {
            buildRoute = buildString {
                append(routeName)
                params.forEach {
                    append("/")
                    append(it)
                }
            }
        }
        this.navHostController.navigate(buildRoute) {
            popUpTo(navHostController.graph.startDestinationId) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    /**
     * Navigation back and then close the app
     *
     * @throws IllegalArgumentException from navHostController
     */
    override fun navigateBackAndClose() {
        navigateUp()
        event.sendEventToApp("EXIT")
    }
    //end region
}