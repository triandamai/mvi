package app.trian.mvi.feature.quiz.detailQuiz

import android.os.Parcelable
import app.trian.mvi.data.model.Quiz
import app.trian.mvi.ui.internal.contract.MviState
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class DetailQuizState(
    val showContent: Boolean = false,
    val quizId: String = "",
    val quiz: @RawValue Quiz = Quiz(),
    override val effect:@RawValue DetailQuizEffect = DetailQuizEffect.Nothing
) : MviState<DetailQuizEffect>(),Parcelable {
}

