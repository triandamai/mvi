/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.internal.listener

interface NavigationListener {
    fun navigateUp()
    fun navigateBack()
    fun navigate(routeName: String, vararg params: String)
    fun navigateSingleTop(routeName: String, vararg params: String)
    fun navigateAndReplace(routeName: String, vararg params: String)
    fun navigateBackAndClose()
}