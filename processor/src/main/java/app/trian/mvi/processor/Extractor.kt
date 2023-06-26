/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.processor

import app.trian.mvi.NavType
import app.trian.mvi.processor.model.NavArgument
import app.trian.mvi.processor.model.Module
import app.trian.mvi.processor.model.NavGroup
import app.trian.mvi.processor.model.Nav
import app.trian.mvi.processor.model.Screen
import app.trian.mvi.processor.model.ScreenDependencies
import app.trian.mvi.processor.model.ViewModel
import com.google.devtools.ksp.getClassDeclarationByName
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.squareup.kotlinpoet.MemberName
import java.io.FileNotFoundException

fun getNavigationGroup(
    classDeclaration: KSClassDeclaration
): NavGroup {
    val annotation = classDeclaration.annotations.getAnnotation("NavigationGroup").first()
    val route = annotation.arguments.getParameterValue<String>("route")
    val startDestination = annotation.arguments.getParameterValue<String>("startDestination")

    return NavGroup(route, startDestination)
}

fun getFunctionPayload(
    functionDeclaration: KSFunctionDeclaration,
    resolver: Resolver
): Nav {
    val location = functionDeclaration.packageName.asString()
    val screenName = functionDeclaration.simpleName.asString()

    val params = functionDeclaration.parameters

    val paramsEventName = params.map { it }
        .firstOrNull { it.type.resolve().declaration.simpleName.asString() == "BaseEventListener" }

    val paramsUiContractName = params.map { it }
        .firstOrNull { it.type.resolve().declaration.simpleName.asString() == "UIContract" }
        ?: throw java.lang.IllegalArgumentException("UIContract must be defined at $screenName")


    val annotation: KSAnnotation =
        functionDeclaration.annotations.getAnnotation("Navigation").first()
    val arg =
        functionDeclaration.annotations.getAnnotation("Argument")
    val deepLink =
        functionDeclaration.annotations.getAnnotation("DeepLink")


    val route = annotation.arguments.getParameterValue<String>("route")
    val parent = annotation.arguments.getParameterValue<String>("parentRoute")
    val group = annotation.arguments.getParameterValue<String>("group")
    val viewModel = annotation.arguments.getParameterValue<KSType>("viewModel").declaration
    val viewModelLocation =
        viewModel.packageName.asString().plus(".").plus(viewModel.simpleName.asString())

    val argParams = arg.map {
        val name = it.arguments.getParameterValue<String>("name")
        val type = it.arguments.getParameterValue<KSType>("navType")
            .declaration.simpleName.asString()

        val navType = NavType.valueOf(type)

        NavArgument(
            name,
            navType
        )

    }
    val deepLinkParam = deepLink.map {
        it.arguments.getParameterValue<String>("uri")
    }

    val findViewModelFile = resolver.getClassDeclarationByName(viewModelLocation)
        ?: throw FileNotFoundException("Cannot find ViewModel ${viewModel.packageName.asString()}.${viewModel.simpleName.asString()}")

    val viewModelSuperType = findViewModelFile.superTypes.map {
        it.resolve().declaration.simpleName.asString()
    }.first().toString()

    if (viewModelSuperType !in listOf("MviViewModel")) {
        throw IllegalArgumentException("$viewModelLocation must extends MviViewModel")
    }

    return Nav(
        route = route,
        parent = parent,
        group = group.ifEmpty { notNestedNavigation },
        arguments = argParams,
        deepLink = deepLinkParam,
        screen = Screen(
            name = screenName,
            locationPackage = location,
            eventContract = if (paramsEventName == null) null else ScreenDependencies(
                memberName = MemberName(
                    paramsEventName.type.resolve().declaration.packageName.asString(),
                    paramsEventName.type.resolve().declaration.simpleName.asString()
                ),
                type = "",
                value = paramsEventName.name?.asString().toString()
            ),
            uiContract = ScreenDependencies(
                memberName = MemberName(
                    paramsUiContractName.type.resolve().declaration.packageName.asString(),
                    paramsUiContractName.type.resolve().declaration.simpleName.asString()
                ),
                type = "",
                value = paramsUiContractName.name?.asString().toString()
            )
        ),
        viewModel = ViewModel(
            locationPackage = viewModel.packageName.asString(),
            name = viewModel.simpleName.asString()
        )
    )
}


fun getModuleName(resolver: Resolver): Module {

    //get moduleName
    val moduleDescriptor = resolver::class.java
        .getDeclaredField("module")
        .apply {
            isAccessible = true
        }
        .get(resolver)

    val rawName = moduleDescriptor::class.java
        .getMethod("getName")
        .invoke(moduleDescriptor)
        .toString()

    val moduleName = rawName.removeSurrounding("<", ">")
        .removePrefix("feature-")
        .removePrefix("core-")
        .removeSuffix("_debug")
        .removeSuffix("_release")

    return Module(
        rawName = rawName,
        componentName = moduleName.plus("Component"),
        fileName = moduleName.replaceFirstChar { it.uppercaseChar() }.plus("Component")
    )
}