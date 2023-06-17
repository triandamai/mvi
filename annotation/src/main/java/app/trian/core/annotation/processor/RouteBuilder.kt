/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.core.annotation.processor

import app.trian.core.annotation.NavType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName


val pageWrapper = MemberName("app.trian.core.ui", "pageWrapper")
val collectAsState = MemberName("androidx.compose.runtime", "collectAsState")
val listener = MemberName("app.trian.core.ui", "UIListener")
val listenerData = MemberName("app.trian.core.ui", "UIListenerData")
val uiControllerType = ClassName("app.trian.core.ui", "UIController")
val navGraphBuilder = ClassName("androidx.navigation", "NavGraphBuilder")

const val uiControllerName = "uiController"
const val notNestedNavigation = "default"
const val OneSpaces = "     "
const val TwoSpaces = "         "
const val ThreeSpaces = "           "
const val FourSpaces = "                "
const val FiveSpaces = "                    "
const val SixSpaces = "                        "
const val SevenSpaces = "                           "

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
        .addStatement(2, "parent=%S,", parent)
        .addStatement(2, "arguments=listOf(")

    argument.forEach {
        funSpec
            .addStatement(3, "navArgument(\"${it.key}\"){ type = ${it.type.getStringType()} },")
    }
    funSpec
        .addStatement(2, "),")
        .addStatement(2, "deepLinks=listOf(")
    deepLink.forEach {
        funSpec
            .addStatement(3, "navDeepLink { uriPattern =\"$it\" },")
    }

    funSpec
        .addStatement(2, "),")
        .addStatement(2, "controller=$uiControllerName")
    funSpec
        .addStatement(1, "){")


    if (hasData) {
        funSpec
            .addStatement(3, "val state by uiState.%M()", collectAsState)
            .addStatement(3, "val data by uiDataState.%M()", collectAsState)
            .addStatement(
                4,
                "%M(uiEvent=%M(controller=uiController,state=state,data=data,commit=::commit,dispatcher=::dispatch))",
                MemberName(screenPackage, screenName),
                listenerData
            )
    } else {
        funSpec
            .addStatement(3, "val state by uiState.%M()", collectAsState)
            .addStatement(
                4,
                "%M(uiEvent=%M(controller=uiController,state=state,commit=::commit,dispatcher=::dispatch))",
                MemberName(screenPackage, screenName),
                listener
            )
    }
    funSpec.addStatement(1, "}")
}

fun FunSpec.Builder.addStatement(spaces: Int, statement: String, vararg arg: Any): FunSpec.Builder {
    val spaceChar = when (spaces) {
        1 -> OneSpaces
        2 -> TwoSpaces
        3 -> ThreeSpaces
        4 -> FourSpaces
        5 -> FiveSpaces
        6 -> SixSpaces
        7 -> SevenSpaces
        else -> OneSpaces
    }
    this.addStatement("${spaceChar}$statement", *arg)
    return this
}

fun NavType.getStringType() = when (this) {
    NavType.String -> "NavType.StringType"
    NavType.Integer -> "NavType.IntType"
    NavType.Boolean -> "NavType.BoolType"
}