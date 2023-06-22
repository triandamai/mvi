package app.trian.mvi.feature.quiz.detailQuiz

sealed interface DetailQuizAction {
    object GetValue : DetailQuizAction
}

