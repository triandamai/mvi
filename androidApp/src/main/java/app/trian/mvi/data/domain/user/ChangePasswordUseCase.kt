package app.trian.mvi.data.domain.user

import app.trian.mvi.ui.ResultState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    operator fun invoke(newPassword: String): Flow<ResultState<Boolean>> = flow {
        emit(ResultState.Loading)
        try {
            firebaseAuth.currentUser?.updatePassword(
                newPassword
            )
            emit(ResultState.Result(true))
        } catch (e: Exception) {
            emit(ResultState.Error(e.message.orEmpty()))
        }

    }.flowOn(Dispatchers.IO)
}