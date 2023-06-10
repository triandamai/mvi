/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import app.trian.core.ui.UIController
import app.trian.resepku.feature.dashboard.authenticationRoute
import app.trian.resepku.feature.dashboard.createRecipe.CreateRecipe
import app.trian.resepku.feature.dashboard.dashboardRoute

@Composable
fun AppNavigation(
    uiController: UIController
) {
    NavHost(
        navController = uiController.router,
        startDestination = app.trian.resepku.feature.dashboard.createRecipe.CreateRecipe.routeName
    ) {
        authenticationRoute(uiController = uiController)
        dashboardRoute(uiController = uiController)
    }
}