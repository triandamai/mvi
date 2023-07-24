package app.trian.mvi.feature.quiz.detailQuiz

import android.os.Parcelable
import app.trian.mvi.data.model.Quiz
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class DetailQuizState(
    val showContent: Boolean = false,
    val quizId: String = "",
    val quiz: @RawValue Quiz = Quiz(),
    val effect:@RawValue DetailQuizEffect = DetailQuizEffect.Nothing
) : Parcelable {
}

