/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.data.domain.quiz

import app.trian.mvi.data.model.Quiz
import app.trian.mvi.sqldelight.Database
import app.trian.mvi.ui.ResultStateData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetListQuizUseCase @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val db: Database
) {
    suspend operator fun invoke(): Flow<ResultStateData<List<Quiz>>> = flow {
        emit(ResultStateData.Loading)
        try {
            val quiz = firebaseFirestore
                .collection("QUIZ")
                .get()
                .await()
                .documents
                .map { it.toObject(Quiz::class.java)!! }
                .sortedByDescending { it.question.size }

            emit(ResultStateData.Result(quiz))
        } catch (e: Exception) {
            emit(ResultStateData.Error(e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)
}