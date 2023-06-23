/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.processor

import app.trian.mvi.NavType
import app.trian.mvi.processor.model.NavArgument
import app.trian.mvi.processor.model.Screen
import app.trian.mvi.processor.model.ScreenDependencies
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName

val pageWrapper = MemberName("app.trian.mvi.ui", "pageWrapper")
val collectAsState = MemberName("androidx.compose.runtime", "collectAsState")
val contract = MemberName("app.trian.mvi.ui.internal", "UIContract")
val uiControllerType = ClassName("app.trian.mvi.ui.internal", "UIController")
val eventType = ClassName("app.trian.mvi.ui.internal.listener", "BaseEventListener")
val navGraphBuilder = ClassName("androidx.navigation", "NavGraphBuilder")

const val uiControllerName = "uiController"
const val eventName = "event"
const val notNestedNavigation = "default"

fun buildNestedNavigation(
    funSpec: FunSpec.Builder,
    route: String,
    startDestination: String,
    buildPage: (FunSpec.Builder) -> Unit
) = with(funSpec) {
    addStatement("navigation(")
    addStatement(1, "route=%S,", route)
    addStatement(1, "startDestination=%S", startDestination)
    addStatement("){")
    buildPage(funSpec)
    addStatement("}")
}

fun buildPageWrapper(
    funSpec: FunSpec.Builder,
    route: String,
    parent: String,
    arguments: List<NavArgument>,
    deepLinks: List<String>,
    screen: Screen,
    viewModelName: String,
    viewModelPackage: String,
) = with(funSpec) {
    addComment("Navigation for ${screen.locationPackage}.${screen.name}")
    addStatement(1, "%M<%T>(", pageWrapper, ClassName(viewModelPackage, viewModelName))
    buildWrapperParams(funSpec, createRoute(route, arguments), parent, arguments, deepLinks)
    addStatement(1, "){")
    buildCollectState(funSpec)
    buildScreen(funSpec, screen)
    addStatement(1, "}")
}

fun buildWrapperParams(
    funSpec: FunSpec.Builder,
    route: String,
    parent: String,
    argument: List<NavArgument>,
    deepLinks: List<String>
) = with(funSpec) {

    addStatement(2, "route=%S,", route)
    addStatement(2, "parent=%S,", parent)
    addStatement(2, "controller=$uiControllerName,")
    buildNavArgument(funSpec, argument)
    buildDeeplink(funSpec, deepLinks)

}

fun createRoute(route: String, argument: List<NavArgument>): String {
    return buildString {
        append(route)
        if (argument.isNotEmpty()) {
            argument.forEach {
                append("/")
                append("{${it.key}}")
            }
        }
    }
}

fun buildNavArgument(
    funSpec: FunSpec.Builder,
    arguments: List<NavArgument>
) = with(funSpec) {
    if (arguments.isEmpty()) {
        addStatement(2, "arguments=listOf(),")
    } else {
        addStatement(2, "arguments=listOf(")
        arguments.forEach {
            addStatement(
                3,
                "navArgument(%S){ type = ${it.type.getStringType()} },",
                it.key
            )
        }
        addStatement(2, "),")
    }
}

fun buildDeeplink(
    funSpec: FunSpec.Builder,
    deepLinks: List<String>
) = with(funSpec) {
    if (deepLinks.isEmpty()) {
        addStatement(2, "deepLinks=listOf(),")
    } else {
        addStatement(2, "deepLinks=listOf(")
        deepLinks.forEach {
            addStatement(3, "navDeepLink { uriPattern =%S },", it)
        }
        addStatement(2, "),")
    }

}

fun buildCollectState(funSpec: FunSpec.Builder) = with(funSpec) {
    addComment("Collecting state from ViewModel")
    addStatement(3, "val state by uiState.%M()", collectAsState)
    addStatement(3, "val intent by intent.%M(initial=null)", collectAsState)
}

fun buildScreen(
    funSpec: FunSpec.Builder,
    screen: Screen
) = with(funSpec) {
    addStatement(3, "%M(", MemberName(screen.locationPackage, screen.name))
    addStatement(
        4,
        "${screen.uiContract.value}=%M(controller=uiController,state=state,intent=intent,mutation=::commit,dispatcher=::dispatch),",
        screen.uiContract.memberName
    )
    addStatement(
        4,
        "${screen.eventContract.value}=$eventName"
    )
    addStatement(3, ")")
}


fun NavType.getStringType() = when (this) {
    NavType.String -> "NavType.StringType"
    NavType.Integer -> "NavType.IntType"
    NavType.Boolean -> "NavType.BoolType"
}

fun FunSpec.Builder.addStatement(
    indentation: Int,
    statement: String,
    vararg arg: Any
): FunSpec.Builder {
    val buildSpace = buildString {
        for (i in 0..indentation) {
            append("  ")
        }
    }
    this.addStatement("$buildSpace$statement", *arg)
    return this
}
