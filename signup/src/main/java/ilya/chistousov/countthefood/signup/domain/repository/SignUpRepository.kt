package ilya.chistousov.countthefood.signup.domain.repository

interface SignUpRepository {
    suspend fun signUpWithEmailAndPassword(
        email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit
    )

    suspend fun signUpWithGoogle(idToken: String, onSuccess: () -> Unit, onFailure: () -> Unit)
}