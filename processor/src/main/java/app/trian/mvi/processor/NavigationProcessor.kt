/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.processor

import app.trian.mvi.Navigation
import app.trian.mvi.NavigationGroup
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ksp.writeTo
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

val importList: MutableList<Pair<String, String>> = mutableListOf(
    Pair("androidx.compose.runtime", "getValue"),
    Pair("androidx.navigation", "NavType"),
    Pair("androidx.navigation", "navArgument"),
    Pair("androidx.navigation", "navDeepLink"),
    Pair("androidx.navigation", "navigation"),
    Pair("kotlin.collections", "listOf"),
)

class NavigationProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val findNavigationGroupAnnotation =
            resolver.findClassAnnotations(NavigationGroup::class).toList()

        //collection function with annotation
        val findNavigationAnnotation =
            resolver.findFunctionAnnotations(Navigation::class).toList()


        //skip if there no annotation
        if (!findNavigationAnnotation.iterator().hasNext()) return emptyList()


        val pageDeclaration = findNavigationAnnotation.map {
            getFunctionPayload(it, resolver, environment.logger)
        }.groupBy { it.group }


        val nestedDeclaration = findNavigationGroupAnnotation.map {
            getNavigationGroup(it)
        }

        val module = getModuleName(resolver)

        val createFunctionRoute = FunSpec
            .builder(module.componentName)
            .receiver(navGraphBuilder)
            .addParameter(
                uiControllerName,
                uiControllerType
            )
            .addParameter(
                eventName,
                eventType
            )

        //generate navigation
        pageDeclaration.forEach { (group, data) ->
            //not a nested navigation
            if (group == notNestedNavigation) {
                data.forEach {
                    importList.addAll(
                        listOf(
                            Pair(it.screen.locationPackage, it.screen.name),
                            Pair(it.viewModel.locationPackage, it.viewModel.name)
                        )
                    )
                    buildPageWrapper(
                        funSpec = createFunctionRoute,
                        arguments = it.arguments,
                        deepLinks = it.deepLink,
                        parent = it.parent,
                        route = it.route,
                        screen = it.screen,
                        viewModelName = it.viewModel.name,
                        viewModelPackage = it.viewModel.locationPackage,
                    )
                }
            } else {
                val findNested = nestedDeclaration.firstOrNull { it.route == group }
                    ?: throw IllegalArgumentException("Navigation Group for $group at ${module.rawName} not found,try create class or interface with annotation @NavigationGroup")
                createFunctionRoute.addComment("Navigation for ${findNested.route}")
                buildNestedNavigation(
                    funSpec = createFunctionRoute,
                    route = findNested.route,
                    startDestination = findNested.startDestination,
                    buildPage = { file ->
                        data.forEach {
                            importList.addAll(
                                listOf(
                                    Pair(it.screen.locationPackage, it.screen.name),
                                    Pair(it.viewModel.locationPackage, it.viewModel.name)
                                )
                            )
                            buildPageWrapper(
                                funSpec = file,
                                arguments = it.arguments,
                                deepLinks = it.deepLink,
                                parent = it.parent,
                                route = it.route,
                                screen = it.screen,
                                viewModelName = it.viewModel.name,
                                viewModelPackage = it.viewModel.locationPackage,
                            )
                        }
                    }
                )
            }
        }
        val generatorFile = FileSpec
            .builder(packageName = "app.trian.mvi", fileName = module.fileName)
            .addFileComment("Generated file,do not edit this file! \n")
            .addFileComment("Copyright 2023. trian.app")

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

    private fun Resolver.findFunctionAnnotations(
        kClass: KClass<*>
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString()
    ).filterIsInstance<KSFunctionDeclaration>().filter {
        val types = it.parameters.map { it.type.resolve().declaration.simpleName.asString() }
        val uiContract = "UIContract" in types
        (uiContract)
    }

    private fun Resolver.findClassAnnotations(
        kClass: KClass<*>
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString()
    ).filterIsInstance<KSClassDeclaration>().filter {
        it.classKind == ClassKind.INTERFACE || it.classKind == ClassKind.CLASS || it.classKind == ClassKind.OBJECT
    }
}
