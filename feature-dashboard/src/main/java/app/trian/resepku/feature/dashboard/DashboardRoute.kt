/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import app.trian.core.ui.UIController
import app.trian.core.ui.UIListenerData
import app.trian.core.ui.pageWrapper
import app.trian.core.ui.routes.Routes.Splash
import app.trian.resepku.feature.dashboard.home.HomeViewModel
import app.trian.resepku.feature.dashboard.home.ScreenHome


fun NavGraphBuilder.dashboardRoute(
    uiController: UIController
) {

    pageWrapper<HomeViewModel>(
        route = Splash.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenHome(
            uiEvent = UIListenerData(
                controller = uiController,
                state = state,
                data = data,
                commit = ::commit,
                commitData = ::commitData,
                dispatcher = ::dispatch
            )
        )
    }
}