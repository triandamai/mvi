/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.feature.quiz.detailQuiz

import android.os.Parcelable
import app.trian.mvi.data.model.Quiz
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class DetailQuizState(
    val showContent:Boolean=false,
    val quizId:String=""
) :  Parcelable {
}

@Immutable
@Parcelize
data class DetailQuizDataState(
    val quiz: @RawValue Quiz = Quiz()
) :  Parcelable {
}


sealed interface DetailQuizEvent {
}