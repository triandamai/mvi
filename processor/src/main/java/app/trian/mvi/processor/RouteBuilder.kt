/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.processor

import app.trian.mvi.NavType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName

val pageWrapper = MemberName("app.trian.mvi.ui", "pageWrapper")
val collectAsState = MemberName("androidx.compose.runtime", "collectAsState")
val listener = MemberName("app.trian.mvi.ui.internal", "UIListener")
val listenerData = MemberName("app.trian.mvi.ui.internal", "UIListenerData")
val uiControllerType = ClassName("app.trian.mvi.ui.internal", "UIController")
val navGraphBuilder = ClassName("androidx.navigation", "NavGraphBuilder")

const val uiControllerName = "uiController"
const val notNestedNavigation = "default"

fun buildPageWrapper(
    funSpec: FunSpec.Builder,
    route: String,
    parent: String,
    argument: Array<NavArgument>,
    deepLink: Array<String>,
    screenName: String,
    screenPackage: String,
    viewModelName: String,
    viewModelPackage: String,
    hasData: Boolean = false
) {
    val buildRoute = buildString {
        append(route)
        if (argument.isNotEmpty()) {
            argument.forEach {
                append("/")
                append("{${it.key}}")
            }
        }
    }
    funSpec
        .addStatement(1, "%M<%T>(", pageWrapper, ClassName(viewModelPackage, viewModelName))
        .addStatement(2, "route=%S,", buildRoute)
    if (parent.isNotEmpty()) {
        funSpec
            .addStatement(2, "parent=%S,", parent)
    }
    buildNavArgument(funSpec, argument)
    buildDeeplink(funSpec, deepLink)
    funSpec
        .addStatement(2, "controller=$uiControllerName")
        .addStatement(1, "){")
    buildCollectState(funSpec, hasData)
    buildScreen(funSpec, screenName, screenPackage, hasData)
    funSpec.addStatement(1, "}")
}

fun buildNavArgument(
    funSpec: FunSpec.Builder,
    arguments: Array<NavArgument>
) {
    if (arguments.isEmpty()) {
        funSpec.addStatement(2, "arguments=listOf(),")
    } else {
        funSpec.addStatement(2, "arguments=listOf(")
        arguments.forEach {
            funSpec.addStatement(
                3,
                "navArgument(\"${it.key}\"){ type = ${it.type.getStringType()} },"
            )
        }
        funSpec.addStatement(2, "),")
    }
}

fun buildDeeplink(
    funSpec: FunSpec.Builder,
    deepLinks: Array<String>
) {
    if (deepLinks.isEmpty()) {
        funSpec.addStatement(2, "deepLinks=listOf(),")
    } else {
        funSpec.addStatement(2, "deepLinks=listOf(")
        deepLinks.forEach {
            funSpec.addStatement(3, "navDeepLink { uriPattern =\"$it\" },")
        }
        funSpec
            .addStatement(2, "),")
    }

}

fun buildCollectState(funSpec: FunSpec.Builder, hasData: Boolean) {
    funSpec
        .addStatement(3, "val state by uiState.%M()", collectAsState)
    if (hasData) {
        funSpec.addStatement(3, "val data by uiDataState.%M()", collectAsState)
    }
}

fun buildScreen(
    funSpec: FunSpec.Builder,
    screenName: String,
    screenPackage: String,
    hasData: Boolean
) {
    funSpec
        .addStatement(
            3,
            "%M(",
            MemberName(screenPackage, screenName)
        )

        .addStatement(4, "uiEvent=%M(", if (hasData) listenerData else listener)
        .addStatement(6, "controller=uiController,")
        .addStatement(6, "state=state,")
        .addStatement(6, "mutation=::commit,")
    if (hasData) {
        funSpec
            .addStatement(6, "data=data,")
            .addStatement(6, "mutationData=::commitData,")
    }
    funSpec
        .addStatement(6, "dispatcher=::dispatch")
        .addStatement(4, ")")
        .addStatement(3, ")")

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
