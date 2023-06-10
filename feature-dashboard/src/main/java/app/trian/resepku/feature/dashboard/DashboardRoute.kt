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
import app.trian.resepku.feature.dashboard.dashboard.DashboardDataState
import app.trian.resepku.feature.dashboard.dashboard.DashboardState
import app.trian.resepku.feature.dashboard.dashboard.DashboardViewModel
import app.trian.resepku.feature.dashboard.dashboard.ScreenDashboard


fun NavGraphBuilder.dashboardRoute(
    uiController: UIController
) {

    pageWrapper<DashboardViewModel>(
        route = Splash.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenDashboard(
            state=state,
            data=data,
            invoker = UIListenerData(
                controller = uiController,
                state = DashboardState(),
                data = DashboardDataState(),
                commit = ::commit,
                commitData = ::commitData,
                dispatcher = ::dispatch
            )
        )
    }
}