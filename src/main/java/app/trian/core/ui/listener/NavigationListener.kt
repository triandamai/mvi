/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.ui.listener

interface NavigationListener {
    fun navigateUp()
    fun navigate(route: String, vararg params: String)
    fun navigateSingleTop(route: String, vararg params: String)
    fun navigateAndReplace(route: String, vararg params: String)
    fun navigateBackAndClose(route: String, vararg params: String)
}