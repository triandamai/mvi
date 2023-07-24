package app.trian.mvi.feature.quiz.listQuiz

import app.trian.mvi.data.domain.quiz.GetListQuizUseCase
import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListQuizViewModel @Inject constructor(
    private val getListQuizUseCase: GetListQuizUseCase
) : MviViewModel<ListQuizState, ListQuizAction>(
    ListQuizState(),
) {
    init {
        getListQuiz()
    }

    private fun getListQuiz() = async {
        getListQuizUseCase()
            .onEach(
                loading = {},
                error = { _, _ -> },
                success = {},
                empty = {}
            )
    }

    override fun onAction(action: ListQuizAction) {
        when (action) {
            ListQuizAction.Nothing -> {

            // sendUiEvent(BaseUIEvent.Navigate(DetailQuiz.routeName,"sasas"))
            }

            ListQuizAction.Navigate -> {
                commit { copy(effect=ListQuizEffect.DetailQuiz("sasas")) }
            }
        }
    }

}