/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.processor

import app.trian.mvi.Navigation
import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ksp.writeTo
import kotlin.reflect.KClass


class NavigationProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {

        //collection function with annotation
        val findNavigationAnnotation =
            resolver.findAnnotations(Navigation::class, environment.logger).toList()
        //skip if there no annotation
        if (!findNavigationAnnotation.iterator().hasNext()) return emptyList()

        val functionDeclarations = findNavigationAnnotation.map {
            getFunctionPayload(it, resolver, environment.logger)
        }.groupBy { it.group }.map {
            Pair(
                NavigationGroup(
                    it.key,
                    it.value.firstOrNull()?.startDestination.orEmpty()
                ),
                it.value
            )
        }

        val module = getModuleName(resolver)
        val importList: MutableList<Pair<String, String>> = mutableListOf(
            Pair("androidx.compose.runtime", "getValue"),
            Pair("androidx.navigation", "NavType"),
            Pair("androidx.navigation", "navArgument"),
            Pair("androidx.navigation", "navDeepLink"),
            Pair("androidx.navigation", "navigation"),
            Pair("kotlin.collections", "listOf"),
        )


        val createFunctionRoute = FunSpec
            .builder(module.componentName)
            .receiver(navGraphBuilder)
            .addParameter(
                uiControllerName,
                uiControllerType
            )

        //generate navigation
        functionDeclarations.forEach { (group, data) ->
            //not a nested navigation
            if (group.route == notNestedNavigation) {
                data.forEach {
                    importList.addAll(
                        listOf(
                            Pair(it.screenPackage, it.screenName),
                            Pair(it.viewModelPackage, it.viewModelName)
                        )
                    )
                    buildPageWrapper(
                        funSpec = createFunctionRoute,
                        argument = it.arguments,
                        deepLink = it.deepLink,
                        parent = it.parent,
                        route = it.route,
                        screenName = it.screenName,
                        screenPackage = it.screenPackage,
                        viewModelName = it.viewModelName,
                        viewModelPackage = it.viewModelPackage,
                        hasData = it.isStateWithData
                    )
                }
            } else {
                //todo::create nested
            }
        }

        val generatorFile = FileSpec
            .builder(packageName = "app.trian.mvi", fileName = module.fileName)
            .addFileComment("Generated file, not supposed to edit this file! \n")
            .addFileComment("copyright 2023. trian.app")

        importList.forEach { generatorFile.addImport(it.first, it.second) }

        generatorFile.addFunction(createFunctionRoute.build())


        //create file for each module
        generatorFile.build().writeTo(
            environment.codeGenerator,
            Dependencies(
                false,
                *findNavigationAnnotation.mapNotNull { it.containingFile }.toList().toTypedArray(),
            )
        )
        return (findNavigationAnnotation).filterNot { it.validate() }.toList()

    }

    private fun Resolver.findAnnotations(
        kClass: KClass<*>,
        logger: KSPLogger
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString()
    ).filterIsInstance<KSFunctionDeclaration>().filter {
        val name = it.parameters.first().type.resolve().declaration.simpleName.asString()
        (name == "UIListener" || name == "UIListenerData")
    }
}
