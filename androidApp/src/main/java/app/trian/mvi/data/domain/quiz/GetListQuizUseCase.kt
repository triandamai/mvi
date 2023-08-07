/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.trian.mvi.data.domain.quiz

import app.trian.mvi.data.model.ResultStateData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetListQuizUseCase @Inject constructor(
) {
    suspend operator fun invoke(): Flow<ResultStateData<List<String>>> = flow {
        emit(ResultStateData.Loading)

    }.flowOn(Dispatchers.IO)
}