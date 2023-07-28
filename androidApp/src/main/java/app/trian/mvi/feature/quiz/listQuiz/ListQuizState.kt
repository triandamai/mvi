package app.trian.mvi.feature.quiz.listQuiz

import android.os.Parcelable
import app.trian.mvi.data.model.Quiz
import app.trian.mvi.data.utils.dummyQuiz
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.internal.contract.MviState
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ListQuizState(
    val a: String = String.Empty,
    val quiz: @RawValue List<Quiz> = dummyQuiz,

    override val effect: @RawValue ListQuizEffect = ListQuizEffect.Nothing
) :MviState<ListQuizEffect>(), Parcelable

