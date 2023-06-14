/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.core.ui

import androidx.annotation.StringRes

sealed class ResultState<out R> {
    object Loading : ResultState<Nothing>()
    data class Result<out Result>(val data: Result) : ResultState<Result>()
    data class Error(
        val message: String = "",
        @StringRes val stringId: Int = 0
    ) : ResultState<Nothing>()
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

sealed class ResponseWithProgress<out R> {
    object Loading : ResponseWithProgress<Nothing>()
    data class Finish<out Result>(val data: Result) : ResponseWithProgress<Result>()
    data class Progress(val progress: Int) : ResponseWithProgress<Nothing>()
    data class Error(val message: String = "", val code: Int = 0) : ResponseWithProgress<Nothing>()
}

