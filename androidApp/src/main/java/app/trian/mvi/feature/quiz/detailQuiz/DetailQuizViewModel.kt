package app.trian.mvi.feature.quiz.detailQuiz

import androidx.lifecycle.SavedStateHandle
import app.trian.mvi.data.domain.quiz.GetDetailQuizUseCase
import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailQuizViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getDetailQuizUseCase: GetDetailQuizUseCase
) : MviViewModel<DetailQuizState, DetailQuizIntent, DetailQuizAction>(
    DetailQuizState(),
) {
    init {
        getQuiz()
    }

    private fun quizId(): String = savedStateHandle.get<String>(DetailQuiz.argKey).orEmpty()

    private fun getQuiz() = async {
        commit {
            copy(
                quizId = quizId()
            )
        }
        getDetailQuizUseCase(quizId())
            .onEach(
                loading = {},
                error = { _, _ -> },
                success = {

                }
            )
    }

    override fun onAction(action: DetailQuizAction) {
        //not empty

    }


}