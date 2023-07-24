package app.trian.mvi.feature.quiz.listQuiz

sealed interface ListQuizEffect {
    object Nothing : ListQuizEffect

    data class DetailQuiz(val params:String):ListQuizEffect

}