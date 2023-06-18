/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.data.model

import app.trian.mvi.ui.extensions.Empty

data class QuizQuestion(
    val id: String = String.Empty,
    val question: String = String.Empty,
    val image: String = "http://via.placeholder.com/640x360",
    val answer: List<String> = listOf(),
    val correctAnswer: String = String.Empty
)
