/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.feature.quiz.detailQuiz

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import app.trian.mvi.data.domain.quiz.GetDetailQuizUseCase
import app.trian.mvi.ui.viewModel.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class DetailQuizViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val getDetailQuizUseCase: GetDetailQuizUseCase
) : BaseViewModelData<DetailQuizState, DetailQuizDataState, DetailQuizEvent>(
    context,
    DetailQuizState(),
    DetailQuizDataState()
) {
    init {
        handleActions()
        getQuiz()
    }

    private fun quizId(): String = savedStateHandle.get<String>(DetailQuiz.argKey).orEmpty()

    private fun getQuiz() = async {
        commit {
            copy(
                quizId=quizId()
            )
        }
        getDetailQuizUseCase(quizId())
            .onEach(
                loading = {},
                error = {},
                success = {
                    commitData { copy(quiz = it) }
                }
            )
    }

    override fun handleActions() = onEvent {}

}