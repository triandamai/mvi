/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard.splashScreen

import app.trian.core.ui.extensions.navigateAndReplace
import app.trian.core.ui.routes.Routes
import app.trian.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel<SplashState, SplashEvent>(SplashState()) {

    init {
        handleActions()
    }

    private fun checkIfUserLoggedIn() = async {
//        if (checkSessionUseCase()) navigateAndReplaceAll(Home.routeName)
//        else
            controller.navigateAndReplace(Routes.Onboard.routeName)
    }


    override fun handleActions() = onEvent {
        when (it) {
            SplashEvent.CheckSession -> checkIfUserLoggedIn()
        }
    }
}