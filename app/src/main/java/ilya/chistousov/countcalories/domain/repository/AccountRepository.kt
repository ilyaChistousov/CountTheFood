package ilya.chistousov.countcalories.domain.repository

interface AccountRepository {

    suspend fun signUpWithEmailAndPassword(
        email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit
    )

    suspend fun signUpWithGoogle(idToken: String, onSuccess: () -> Unit, onFailure: () -> Unit)

    suspend fun signInWithGoogle(idToken: String, onSuccess: () -> Unit, onFailure: () -> Unit)

    suspend fun signInWithEmailAndPassword(
        email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit
    )

    suspend fun resetPassword(email: String, onSuccess: () -> Unit, onFailure: () -> Unit)
}