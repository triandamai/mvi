/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.dashboard

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import app.trian.core.ui.UIController
import app.trian.core.ui.UIListener
import app.trian.core.ui.pageWrapper
import app.trian.core.ui.routes.AuthenticationConstants
import app.trian.core.ui.routes.Routes.ChangePassword
import app.trian.core.ui.routes.Routes.Onboard
import app.trian.core.ui.routes.Routes.ResetPassword
import app.trian.core.ui.routes.Routes.SignIn
import app.trian.core.ui.routes.Routes.SignUp
import app.trian.core.ui.routes.Routes.Splash
import app.trian.resepku.feature.dashboard.changePassword.ChangePasswordViewModel
import app.trian.resepku.feature.dashboard.changePassword.ScreenChangePassword
import app.trian.resepku.feature.dashboard.dashboard.OnboardViewModel
import app.trian.resepku.feature.dashboard.dashboard.ScreenOnboard
import app.trian.resepku.feature.dashboard.resetPassword.ResetPasswordViewModel
import app.trian.resepku.feature.dashboard.resetPassword.ScreenResetPassword
import app.trian.resepku.feature.dashboard.signIn.ScreenSignIn
import app.trian.resepku.feature.dashboard.signIn.SignInViewModel
import app.trian.resepku.feature.dashboard.signUp.ScreenSignUp
import app.trian.resepku.feature.dashboard.signUp.SignUpViewModel
import app.trian.resepku.feature.dashboard.splashScreen.ScreenSplash
import app.trian.resepku.feature.dashboard.splashScreen.SplashState
import app.trian.resepku.feature.dashboard.splashScreen.SplashViewModel


fun NavGraphBuilder.authenticationRoute(
    uiController: UIController
) {
    navigation(
        route = AuthenticationConstants.parentRoute,
        startDestination = Splash.routeName
    ) {
        pageWrapper<SplashViewModel>(
            route = Splash.routeName,
            controller = uiController
        ) {
            ScreenSplash(
                invoker = UIListener(
                    controller = uiController,
                    state = SplashState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<OnboardViewModel>(
            route = Onboard.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenOnboard(
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<SignInViewModel>(
            route = SignIn.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenSignIn(
                invoker = UIListener(
                    controller = uiController,
                    state =state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<SignUpViewModel>(
            route = SignUp.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenSignUp(
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<ChangePasswordViewModel>(
            route = ChangePassword.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenChangePassword(
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<ResetPasswordViewModel>(
            route = ResetPassword.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenResetPassword(
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

    }
}