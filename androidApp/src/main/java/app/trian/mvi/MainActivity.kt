package app.trian.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import app.trian.mvi.feature.quiz.listQuiz.ListQuiz
import app.trian.mvi.ui.BaseMainApp
import app.trian.mvi.ui.UIController
import app.trian.mvi.ui.listener.BaseEventListener
import app.trian.mvi.ui.listener.EventListener
import app.trian.mvi.ui.rememberUIController
import app.trian.ksp.androidAppComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var eventListener: BaseEventListener
    private lateinit var uiController: UIController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            eventListener = EventListener()
            uiController = rememberUIController(
                event = eventListener
            )
            BaseMainApp(uiController) {
                NavHost(
                    navController = uiController.router,
                    startDestination = ListQuiz.routeName
                ) {
                    androidAppComponent(it)
                }
            }
        }
    }
}


