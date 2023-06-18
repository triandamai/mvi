package app.trian.mvi.data.domain.user;

import app.trian.mvi.ui.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    operator fun invoke(email: String, password: String): Flow<ResultState<FirebaseUser>> = flow {
        emit(ResultState.Loading)
        try {
            val authenticated = firebaseAuth.signInWithEmailAndPassword(
                email,
                password
            ).await()
            if (authenticated.user == null) {
                emit(ResultState.Error(""))
                return@flow
            }
            emit(ResultState.Result(authenticated.user!!))
        } catch (e: Exception) {
            emit(ResultState.Error(e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)
}
