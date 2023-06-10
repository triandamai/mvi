/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.recipe

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import app.trian.core.ui.UIController
import app.trian.core.ui.UIListenerData
import app.trian.core.ui.pageWrapper
import app.trian.core.ui.routes.Routes.CreateRecipe
import app.trian.resepku.feature.recipe.createRecipe.CreateRecipeDataState
import app.trian.resepku.feature.recipe.createRecipe.CreateRecipeState
import app.trian.resepku.feature.recipe.createRecipe.CreateRecipeViewModel
import app.trian.resepku.feature.recipe.createRecipe.ScreenCreateRecipe


fun NavGraphBuilder.dashboardRoute(
    uiController: UIController
) {

    pageWrapper<CreateRecipeViewModel>(
        route = CreateRecipe.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenCreateRecipe(
            state=state,
            data=data,
            invoker = UIListenerData(
                controller = uiController,
                state = CreateRecipeState(),
                data = CreateRecipeDataState(),
                commit = ::commit,
                commitData = ::commitData,
                dispatcher = ::dispatch
            )
        )
    }
}