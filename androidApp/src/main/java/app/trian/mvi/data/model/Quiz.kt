/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.data.model

import app.trian.mvi.ui.extensions.Empty

data class Quiz(
    val id:String=String.Empty,
    val quizImage: String= "http://via.placeholder.com/640x360",
    val quizTitle:String=String.Empty,
    val quizDescription:String=String.Empty,
    val quizDuration:String=String.Empty,
    val progress:Int=0,
    val question:List<QuizQuestion> = listOf()
)
