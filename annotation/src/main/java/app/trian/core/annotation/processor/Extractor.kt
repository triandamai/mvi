/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.annotation.processor

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
): ValueAnnotation {
    val location = functionDeclaration.packageName.asString()
    val screenName = functionDeclaration.simpleName.asString()

    val annotation: KSAnnotation = functionDeclaration.annotations.first {
        it.shortName.asString() == "Navigation"
    }

    val route = annotation.arguments.first {
        it.name?.asString() == "route"
    }.value as String

    val parent = annotation.arguments.first {
        it.name?.asString() == "parent"
    }.value as String

    val arg = annotation.arguments.first {
        it.name?.asString() == "arguments"
    }.value as ArrayList<*>


    val deepLink = annotation.arguments.first {
        it.name?.asString() == "deeplink"
    }.value as ArrayList<*>

    val viewModel = (annotation.arguments.first {
        it.name?.asString() == "viewModel"
    }.value as KSType).declaration


    val findViewModelFile = resolver.getClassDeclarationByName(
        "${viewModel.packageName.asString()}.${viewModel.simpleName.asString()}"
    )

    val isBaseViewModelData = findViewModelFile?.superTypes?.map {
        it.resolve().declaration.simpleName.asString()
    }?.first().toString() == "BaseViewModelData"

    return ValueAnnotation(
        route = route,
        parent = parent,
        arguments = arg.map { it.toString() }.toTypedArray(),
        deepLink = deepLink.map { it.toString() }.toTypedArray(),
        screenName = screenName,
        screenPackage = location,
        viewModel = viewModel.simpleName.asString(),
        viewModelPackage = viewModel.packageName.asString(),
        isStateWithData = isBaseViewModelData
    )
}
