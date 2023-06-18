/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.components.ItemQuiz
import app.trian.mvi.feature.quiz.detailQuiz.DetailQuiz
import app.trian.mvi.Navigation
import app.trian.mvi.ui.BaseMainApp
import app.trian.mvi.ui.BaseScreen
import app.trian.mvi.ui.UIListenerData
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.rememberUIController

object ListQuiz {
    const val routeName = "ListQuiz"
}


@Navigation(
    route = ListQuiz.routeName,
    viewModel = ListQuizViewModel::class
)
@Composable
internal fun ScreenListQuiz(
    uiEvent: UIListenerData<ListQuizState, ListQuizDataState, ListQuizEvent>
) = UIWrapper(uiEvent) {

    BaseScreen(
        controller=controller,
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
                items(data.quiz) {
                    ItemQuiz(
                        quizName = it.quizTitle,
                        quizImage = it.quizImage,
                        quizProgress = it.progress,
                        quizAmountQuestion = it.question.size,
                        onClick = {

                            router.navigateSingleTop(DetailQuiz.routeName,it.id)
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
        ScreenListQuiz(
            uiEvent = UIListenerData(
                controller = rememberUIController(),
                state = ListQuizState(),
                data = ListQuizDataState()
            )
        )
    }
}