/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.feature.quiz.listQuiz

import android.os.Parcelable
import app.trian.mvi.data.model.Quiz
import app.trian.mvi.data.utils.dummyQuiz
import app.trian.mvi.ui.extensions.Empty
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ListQuizState(
    val a: String = String.Empty
) :  Parcelable {
}


@Immutable
@Parcelize
data class ListQuizDataState(
    val quiz: @RawValue List<Quiz> = dummyQuiz
) :  Parcelable {
}


sealed interface ListQuizEvent {
}