package app.trian.mvi.feature.quiz.listQuiz

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.BaseMainApp
import app.trian.mvi.BaseScreen
import app.trian.mvi.Navigation
import app.trian.mvi.components.ItemQuiz
import app.trian.mvi.feature.quiz.detailQuiz.DetailQuiz
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import app.trian.mvi.ui.internal.rememberUIController

object ListQuiz {
    const val routeName = "ListQuiz"
}


@Navigation(
    route = ListQuiz.routeName,
    viewModel = ListQuizViewModel::class
)
@Composable
internal fun ListQuizScreen(
    uiContract: UIContract<ListQuizState, ListQuizAction>
) = UIWrapper(uiContract) {

    UseEffect(
        key = state.effect,
        onDispose = { copy(effect = ListQuizEffect.Nothing) },
        block = {
            when (this) {
                ListQuizEffect.Nothing -> Unit
                is ListQuizEffect.DetailQuiz -> {
                    navigator.navigate(DetailQuiz.routeName, params)
                }
            }
        }
    )

    BaseScreen(
        topAppBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Daftar Quiz"
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = ""
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.surface,
                elevation = 0.dp
            )
        }
    ) {
        LazyColumn(
            content = {
                items(state.quiz) {
                    ItemQuiz(
                        quizName = it.quizTitle,
                        quizImage = it.quizImage,
                        quizProgress = it.progress,
                        quizAmountQuestion = it.question.size,
                        onClick = {
                            dispatch(ListQuizAction.Navigate)
                            //controller.navigator.navigateSingleTop(DetailQuiz.routeName, it.id)
                        }
                    )
                }
            })

    }

}

@Preview
@Composable
fun PreviewScreenListQuiz() {
    BaseMainApp {
        ListQuizScreen(
            uiContract = UIContract(
                controller = rememberUIController(),
                state = ListQuizState(),
            )
        )
    }
}