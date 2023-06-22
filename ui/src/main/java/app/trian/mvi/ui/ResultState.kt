/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.mvi.ui

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.suspendCancellableCoroutine

sealed class ResultState<out R> {
    object Loading : ResultState<Nothing>()
    data class Result<out Out>(val data: Out) : ResultState<Out>()
    data class Error(val message: String = "", @StringRes val stringId: Int = R.string.app_name) :
        ResultState<Nothing>()
}

sealed class ResultStateData<out R> {
    object Loading : ResultStateData<Nothing>()
    object Empty : ResultStateData<Nothing>()
    data class Result<out Result>(val data: Result) : ResultStateData<Result>()
    data class Error(
        val message: String = "",
        @StringRes val stringId: Int = 0
    ) : ResultStateData<Nothing>()
}

sealed class ResultStateWithProgress<out R> {
    object Loading : ResultStateWithProgress<Nothing>()
    data class Finish<out Result>(val data: Result) : ResultStateWithProgress<Result>()
    data class Progress(val progress: Int) : ResultStateWithProgress<Nothing>()
    data class Error(val message: String = "", @StringRes val stringId: Int = 0) :
        ResultStateWithProgress<Nothing>()
}