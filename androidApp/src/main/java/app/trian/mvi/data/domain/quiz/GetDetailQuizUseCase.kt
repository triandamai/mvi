/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.data.domain.quiz

import app.trian.mvi.R
import app.trian.mvi.data.model.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDetailQuizUseCase @Inject constructor(

) {
    operator fun invoke(quizId: String): Flow<ResultState<Any>> = flow {
        emit(ResultState.Loading)
        try {

            emit(ResultState.Result(""))
        } catch (e: Exception) {
            emit(ResultState.Error(e.message.orEmpty(), R.string.alert_email_empty))
        }
    }.flowOn(Dispatchers.IO)
}