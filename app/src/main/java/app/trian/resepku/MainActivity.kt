package app.trian.resepku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import app.trian.core.ui.BaseMainApp
import app.trian.core.ui.UIController
import app.trian.core.ui.extensions.addOnEventListener
import app.trian.core.ui.listener.ScreenToAppEvent
import app.trian.core.ui.rememberUIController
import app.trian.core.ui.routes.AuthenticationConstants
import app.trian.resepku.feature.authentication.authenticationRoute
import app.trian.resepku.feature.dashboard.dashboardRoute
import app.trian.resepku.feature.recipe.recipeRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var uiController: UIController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            uiController = rememberUIController()
            LaunchedEffect(key1 = uiController, block = {
                uiController.addOnEventListener { event ->
                    when (event) {
                        ScreenToAppEvent.EXIT_APP -> finish()
                    }
                }
            })
            BaseMainApp(
                controller = uiController
            ) {
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
