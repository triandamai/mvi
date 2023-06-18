/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.mvi.ui.routes

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object Routes {
    object CreateRecipe {
        const val routeName = "CreateRecipe"
    }
    object DetailRecipe {
        const val routeName = "DetailRecipe"
        const val argKey = "RecipeId"
        fun routeName() = "$routeName/{$argKey}"

        val navArg = listOf(
            navArgument(argKey) {
                type = NavType.StringType
            }
        )
    }
    object ChangePassword {
        const val routeName = "ChangePassword"
    }
    object CheckEmailResetPassword {
        const val routeName = "CheckEmailResetPassword"
    }

    object CompleteProfile {
        const val routeName = "CompleteProfile"
    }
    object CreateNewPassword {
        const val routeName = "CreateNewPassword"
    }
    object CreatePin {
        const val routeName = "CreatePin"
    }
    object EmailVerification {
        const val routeName = "EmailVerification"
    }
    object InputPin {
        const val routeName = "InputPin"
    }
    object Onboard {
        const val routeName = "Onboard"

    }
    object ResetPassword {
        const val routeName = "ResetPassword"
    }
    object SignIn {
        const val routeName = "SignIn"
    }
    object SignUp {
        const val routeName = "SignUp"
    }

    object Splash {
        const val routeName = "Splash"
    }

    object CreateAccount {
        const val routeName = "CreateAccount"
    }
    object CreateAccountSaving {
        const val routeName = "CreateAccountSaving"
    }

    object CreateBudget {
        const val routeName = "CreateBudget"
    }
    object ListAccount {
        const val routeName = "ListAccount"

        val tabs = listOf(
            "Semua",
            "Tabungan",
            "Investasi"
        )
    }
    object ResultCreateBudget {
        const val routeName = "ResultCreateBudget"
    }
    object TutorialBudget {
        const val routeName = "TutorialBudget"
    }

    object CreatePost {
        const val routeName = "CreatePost"
    }
    object DetailPost {
        const val routeName = "DetailPost"
        const val argKey = "PostId"
        fun routeName() = "$routeName/{$argKey}"

        val navArg = listOf(
            navArgument(argKey) {
                type = NavType.StringType
            }
        )
    }
    object Budget {
        const val routeName = "Budget"
    }
    object Community {
        const val routeName = "Community"
    }

    object Home {
        const val routeName = "Home"
    }

    object Report {
        const val routeName = "Report"
    }
    object EditProfile {
        const val routeName = "EditProfile"
    }

    object Profile {
        const val routeName = "Profile"

    }
    object CreateTransaction {
        const val routeName = "CreateTransaction"
    }

    object DetailTransaction {
        const val routeName = "DetailTransaction"

        const val argKey = "TransactionId"

        fun routeName() = "$routeName/{$argKey}"

        val navArgs = listOf<NamedNavArgument>(
            navArgument(argKey) {
                type = NavType.StringType
            }
        )
    }
    object EditTransaction {
        const val routeName = "EditTransaction"

        const val argKey = "TransactionId"

        fun routeName() = "$routeName/{$argKey}"

        val navArgs = listOf<NamedNavArgument>(
            navArgument(argKey) {
                type = NavType.StringType
            }
        )
    }
    object ListTransaction {
        const val routeName = "ListTransaction"
    }
}