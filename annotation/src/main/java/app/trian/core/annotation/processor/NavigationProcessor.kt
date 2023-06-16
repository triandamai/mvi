/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.annotation.processor

import app.trian.core.annotation.Navigation
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.writeTo
import kotlin.reflect.KClass


class NavigationProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {

        //collection function with annotation
        val listedFunctions = resolver.findAnnotations(Navigation::class).toList()
        //skip if there no annotation
        if (!listedFunctions.iterator().hasNext()) return emptyList()

//        val data = listedFunctions.map {
//            getFunctionPayload(it, resolver, environment.logger)
//        }.groupBy {
//            it.group
//        }

        val moduleName = getModuleName(resolver)
        val importList: MutableList<Pair<String, String>> = mutableListOf()

//        val eventListenerType = ClassName("app.trian.core.ui.listener", "EventListener")
//        uiControllerType.parameterizedBy(eventListenerType)
        val uiControllerType = ClassName("app.trian.core.ui", "UIController")

        val createFunctionRoute = FunSpec
            .builder("${moduleName}Component")
            .receiver(ClassName("androidx.navigation", "NavGraphBuilder"))
            .addParameter(
                "uiController",
                uiControllerType
            )
        listedFunctions.forEach {
            val meta = getFunctionPayload(it, resolver, environment.logger)
            importList.addAll(
                listOf(
                    Pair(meta.screenPackage, meta.screenName),
                    Pair(meta.viewModelPackage, meta.viewModel)
                )
            )

            createFunctionRoute.addStatement(
                buildPageWrapper(
                    route = meta.arguments.buildRouteName(meta.route),
                    args = meta.arguments.buildNvArgument(),
                    parent = meta.parent,
                    deepLink = meta.deepLink.buildDeeplink(),
                    subscriber = buildSubscriberState(meta.isStateWithData),
                    body = buildScreen(
                        meta.screenName,
                        meta.isStateWithData
                    ),
                    viewModelName = meta.viewModel,
                )
            )
        }

        val generatorFile = FileSpec
            .builder(
                packageName = "app.trian.ksp",
                fileName = "${moduleName.replaceFirstChar { it.uppercaseChar() }}Component"
            )
            .addFileComment("Generated file not supposed to edit \n")
            .addFileComment("copyright 2023. trian.app")

        importList.forEach { generatorFile.addImport(it.first, it.second) }

        generatorFile
            .addImport("androidx.compose.runtime", "getValue")
            .addImport("androidx.navigation", "NavType")
            .addImport("androidx.navigation", "navArgument")
            .addImport("androidx.navigation", "navDeepLink")
            .addImport("androidx.compose.runtime", "collectAsState")
            .addImport("app.trian.core.ui", "pageWrapper")
            .addImport("app.trian.core.ui", "UIListenerData")
            .addImport("app.trian.core.ui", "UIListener")
            .addFunction(createFunctionRoute.build())


        //end get
        generatorFile.build().writeTo(
            environment.codeGenerator,
            Dependencies(
                false,
                *listedFunctions.mapNotNull { it.containingFile }.toList().toTypedArray(),
            )
        )

        return (listedFunctions).filterNot { it.validate() }.toList()

    }

    private fun Resolver.findAnnotations(
        kClass: KClass<*>
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString()
    ).filterIsInstance<KSFunctionDeclaration>().filter { it ->
        it.parameters.map { it.name?.asString() }.contains("uiEvent")
    }

    private fun getModuleName(resolver: Resolver): String {

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

        return rawName.removeSurrounding("<", ">")
            .removePrefix("feature-")
            .removePrefix("core-")
            .removeSuffix("_debug")
            .removeSuffix("_release")
    }

    private fun buildPageWrapper(
        viewModelName: String,
        route: String,
        parent: String,
        args: String,
        deepLink: String,
        subscriber: String,
        body: String
    ): String {
        return buildString {
            append(
                """
                 pageWrapper<$viewModelName>(
                    route="$route",
                    parent="$parent",
                    arguments=$args,
                    deepLinks=$deepLink,
                    controller=uiController
                 ){
                    $subscriber
                    $body
                  }
            """.trimIndent()
            )
        }
    }

    private fun buildSubscriberState(hasData: Boolean): String {
        return buildString {
            append("val state by uiState.collectAsState() \n")
            if (hasData) {
                append("val data by uiDataState.collectAsState()")
            }
        }
    }

    private fun buildScreen(
        screenName: String,
        hasData: Boolean
    ): String {
        val listenerClass = if (hasData) "UIListenerData" else "UIListener"
        val mutation = if (hasData) """
            data=data,
            commitData=::commitData,
        """.trimIndent() else ""
        return buildString {
            append(
                """
                $screenName(
                    uiEvent = $listenerClass(
                        controller = uiController,
                        state = state,
                        $mutation
                        commit = ::commit,
                        dispatcher = ::dispatch
                    )
                )
            """.trimIndent()
            )
        }
    }
}