/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.splashScreen

import android.content.Context
import app.trian.core.ui.extensions.navigateAndReplace
import app.trian.core.ui.routes.Routes
import app.trian.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
   @ApplicationContext context: Context
) : BaseViewModel<SplashState, SplashEvent>(context,SplashState()) {

    init {
        handleActions()
        checkIfUserLoggedIn()
    }

    private fun checkIfUserLoggedIn() = async {
//        if (checkSessionUseCase()) navigateAndReplaceAll(Home.routeName)
//        else
        navigation.navigateAndReplace(Routes.Onboard.routeName)
    }


    override fun handleActions() = onEvent {

    }
}