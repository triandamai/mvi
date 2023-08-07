package app.trian.mvi.feature.quiz.listQuiz

import app.trian.mvi.data.domain.quiz.GetListQuizUseCase
import app.trian.mvi.feature.quiz.detailQuiz.DetailQuiz
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
            .collect {

            }
    }

    override fun onAction(action: ListQuizAction) {
        when (action) {
            ListQuizAction.Nothing -> {

                // sendUiEvent(BaseUIEvent.Navigate(DetailQuiz.routeName,"sasas"))
            }

            ListQuizAction.Navigate -> {
                navigate(DetailQuiz.routeName,"sasas")
                    //  commit { copy(effect = ListQuizEffect.DetailQuiz("sasas")) }
            }
        }
    }

}