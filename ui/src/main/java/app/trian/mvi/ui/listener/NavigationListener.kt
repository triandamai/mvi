/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.listener

interface NavigationListener {
    fun navigateUp()
    fun navigate(route: String, vararg params: String)
    fun navigateSingleTop(route: String, vararg params: String)
    fun navigateAndReplace(route: String, vararg params: String)
    fun navigateBackAndClose(route: String, vararg params: String)
}

class NavigationListenerImpl() : NavigationListener {
    override fun navigateUp() {

    }

    override fun navigate(route: String, vararg params: String) {

    }

    override fun navigateSingleTop(route: String, vararg params: String) {

    }

    override fun navigateAndReplace(route: String, vararg params: String) {

    }

    override fun navigateBackAndClose(route: String, vararg params: String) {

    }
}