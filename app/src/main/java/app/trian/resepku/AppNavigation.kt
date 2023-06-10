/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import app.trian.core.ui.UIController
import app.trian.resepku.feature.authentication.authenticationRoute
import app.trian.resepku.feature.createRecipe.CreateRecipe
import app.trian.resepku.feature.createRecipe.routeCreateRecipe
import app.trian.resepku.feature.dashboard.routeDashboard

@Composable
fun AppNavigation(
    uiController: UIController
) {
    NavHost(
        navController = uiController.router,
        startDestination = CreateRecipe.routeName
    ) {
        authenticationRoute(uiController=uiController)
//        routeCreateRecipe(applicationState)
//
//        routeDashboard(applicationState)
    }
}