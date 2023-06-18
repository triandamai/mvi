package app.trian.mvi.data.domain.user

import app.trian.mvi.ui.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpWithEmailAndPasswordUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    operator fun invoke(
        displayName: String,
        email: String,
        password: String
    ): Flow<ResultState<FirebaseUser>> = flow {
        emit(ResultState.Loading)
        try {
            val authenticated = firebaseAuth.createUserWithEmailAndPassword(
                email,
                password
            ).await()
            if (authenticated.user == null) {
                emit(ResultState.Error("Ggal mendaftarkan akun, silahkan coba lagi nanti"))
                return@flow
            }
            val updateProfile = userProfileChangeRequest {
                this.displayName = displayName
            }
            authenticated.user!!.updateProfile(updateProfile)
            authenticated.user!!.sendEmailVerification().await()
            emit(ResultState.Result(authenticated.user!!))
        } catch (e: Exception) {
            emit(ResultState.Error(e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)
}