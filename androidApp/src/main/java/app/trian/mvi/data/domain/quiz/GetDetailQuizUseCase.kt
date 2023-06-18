/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.data.domain.quiz

import app.trian.mvi.data.model.Quiz
import app.trian.mvi.ui.ResultState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetDetailQuizUseCase @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) {
    operator fun invoke(quizId: String): Flow<ResultState<Quiz>> = flow {
        emit(ResultState.Loading)
        try {
            val quiz = firebaseFirestore.collection("QUIZ").document(quizId)
                .get()
                .await()
                .toObject(Quiz::class.java)
            if (quiz == null) {
                emit(ResultState.Error(stringId = R.string.message_failed_fetch_data))
                return@flow
            }

            emit(ResultState.Result(quiz))
        } catch (e: Exception) {
            emit(ResultState.Error(e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)
}