/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.annotation.processor

import app.trian.core.annotation.NavType

data class NavigationModel(
    val route: String,
    val parent: String = "",
    val group: String = "",
    val startDestination: String = "",
    val arguments: Array<NavArgument> = arrayOf(),
    val deepLink: Array<String> = arrayOf(),
    val screenName: String,
    val screenPackage: String,
    val viewModelName: String,
    val viewModelPackage: String,
    val isStateWithData: Boolean = false
)

data class Module(
    val rawName: String,
    val componentName: String,
    val fileName: String
)

data class NavigationGroup(
    val route: String,
    val startDestination: String
)

data class NavArgument(
    val key: String,
    val type: NavType
)
