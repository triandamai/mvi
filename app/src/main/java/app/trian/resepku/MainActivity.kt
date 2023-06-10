package app.trian.resepku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import app.trian.core.ui.BaseMainApp
import app.trian.core.ui.routes.Routes
import app.trian.resepku.feature.dashboard.authenticationRoute
import app.trian.resepku.feature.dashboard.dashboardRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseMainApp {
                NavHost(
                    navController = it.router,
                    startDestination = Routes.CreateRecipe.routeName
                ) {
                    authenticationRoute(uiController = it)
                    dashboardRoute(uiController = it)
                }
            }
        }
    }
}
