/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.trian.resepku.base.ApplicationState
import app.trian.resepku.base.BaseMainApp
import app.trian.resepku.base.BaseScreen
import app.trian.resepku.base.UIWrapper

object Dashboard {
    const val routeName = "Dashboard"
}

fun NavGraphBuilder.routeDashboard(
    state: ApplicationState,
) {
    composable(Dashboard.routeName) {
        ScreenDashboard(appState = state)
    }
}

@Composable
internal fun ScreenDashboard(
    appState: ApplicationState,
) = UIWrapper<DashboardViewModel>(appState = appState) {
    val state by uiState.collectAsState()
    val dataState by uiDataState.collectAsState()

    BaseScreen(
        appState=appState,
        bottomAppBar = {

        }
    ) {

    }
}

@Preview
@Composable
fun PreviewScreenDashboard() {
    BaseMainApp {
        ScreenDashboard(it)
    }
}