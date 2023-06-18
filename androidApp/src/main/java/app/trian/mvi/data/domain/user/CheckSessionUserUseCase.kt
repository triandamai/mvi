package app.trian.mvi.data.domain.user

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckSessionUserUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    operator fun invoke(): Flow<Boolean> = flow {
        emit(firebaseAuth.currentUser != null)
    }
}