/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import app.trian.resepku.base.ApplicationState
import app.trian.resepku.feature.createRecipe.CreateRecipe
import app.trian.resepku.feature.createRecipe.routeCreateRecipe

@Composable
fun AppNavigation(
    applicationState: ApplicationState
) {
    NavHost(
        navController = applicationState.router,
        startDestination = CreateRecipe.routeName
    ) {
        routeCreateRecipe(applicationState)
    }
}