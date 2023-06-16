/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.core.annotation.processor

class ValueAnnotation(
    val route: String,
    val parent: String = "",
    val group:String="",
    val arguments: Array<String> = arrayOf(),
    val deepLink: Array<String> = arrayOf(),
    val screenName:String,
    val screenPackage:String,
    val viewModel: String,
    val viewModelPackage: String,
    val isStateWithData: Boolean = false
)
