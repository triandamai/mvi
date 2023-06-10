/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.trian.resepku.base.BaseMainApp
import app.trian.resepku.base.BaseScreen

@Composable
fun DashboardNavigation() {

}

@Preview
@Composable
fun PreviewDashboardNavigation() {
    BaseMainApp {
        BaseScreen(
            bottomAppBar = {
                DashboardNavigation()
            }
        ) {

        }
    }
}