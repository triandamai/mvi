/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.processor

import app.trian.mvi.NavType
import com.google.devtools.ksp.getClassDeclarationByName
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSType

fun getFunctionPayload(
    functionDeclaration: KSFunctionDeclaration,
    resolver: Resolver,
    logger: KSPLogger
): NavigationModel {
    val location = functionDeclaration.packageName.asString()
    val screenName = functionDeclaration.simpleName.asString()

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

    val findViewModelFile = resolver.getClassDeclarationByName(
        "${viewModel.packageName.asString()}.${viewModel.simpleName.asString()}"
    )

    val isBaseViewModelData = findViewModelFile?.superTypes?.map {
        it.resolve().declaration.simpleName.asString()
    }?.first().toString() == "MviViewModelData"

    return NavigationModel(
        route = route,
        parent = parent,
        group = group.ifEmpty { notNestedNavigation },
        arguments = argParams.toTypedArray(),
        deepLink = deepLinkParam.toTypedArray(),
        screenName = screenName,
        screenPackage = location,
        viewModelName = viewModel.simpleName.asString(),
        viewModelPackage = viewModel.packageName.asString(),
        isStateWithData = isBaseViewModelData
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