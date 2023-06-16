/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.annotation;

import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Navigation(
    val route: String,
    val parent: String = "",
    val arguments: Array<String> = [],
    val deeplink: Array<String> = [],
    val viewModel: KClass<*>
)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class NavigationGroup(
    val route: String,
    val startDestination: String = "",
)