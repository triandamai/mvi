/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi;

import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Navigation(
    val route: String,
    val parentRoute: String = "",
    val group: String = "",
    val viewModel: KClass<*>
)

@Repeatable
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Argument(
    val name: String,
    val navType: NavType = NavType.String,
    val order: Int = 0
)

@Repeatable
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class DeepLink(
    val uri: String,
)

enum class NavType {
    String,
    Integer,
    Boolean
}