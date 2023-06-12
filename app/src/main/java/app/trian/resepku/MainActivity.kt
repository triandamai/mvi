package app.trian.resepku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import app.trian.core.ui.BaseMainApp
import app.trian.core.ui.routes.AuthenticationConstants
import app.trian.resepku.feature.authentication.authenticationRoute
import app.trian.resepku.feature.dashboard.dashboardRoute
import app.trian.resepku.feature.recipe.recipeRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseMainApp {
                NavHost(
                    navController = it.router,
                    startDestination = AuthenticationConstants.parentRoute
                ) {
                    authenticationRoute(uiController = it)
                    dashboardRoute(uiController = it)
                    recipeRoute(uiController = it)
                }
            }
        }
    }
}
