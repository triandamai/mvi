/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.processor

fun Array<String>.buildNvArgument(): String {
        val array = this
        return buildString {
            append("listOf(")
            array.forEach {
                append(
                    """
                    navArgument("$it") {
                     type = NavType.StringType
                    },
                """.trimIndent()
                )
            }
            append(")")
        }
    }

    fun Array<String>.buildRouteName(route: String): String {
        val array = this
        if (array.isEmpty()) return route
        return buildString {
            append(route)
            array.forEach {
                append("/{$it}")
            }
        }
    }

    fun Array<String>.buildDeeplink(): String {
        val array = this
        return buildString {
            append("listOf(")
            array.forEach {
                append(
                    """
                    navDeepLink{
                        uriPattern = "$it"
                    },
                """.trimIndent()
                )
            }
            append(")")

        }
    }