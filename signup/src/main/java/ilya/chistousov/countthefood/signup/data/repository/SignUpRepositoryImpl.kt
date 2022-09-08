package ilya.chistousov.countthefood.signup.data.repository

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import ilya.chistousov.countthefood.signup.domain.repository.SignUpRepository
import java.net.SocketTimeoutException
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val googleClient: GoogleSignInClient
) : SignUpRepository {

    override suspend fun signUpWithEmailAndPassword(
        email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit
    ) {
        try {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                onFailure()
            }
        } catch (ex: SocketTimeoutException) {
            Log.d("SIGN_UP", ex.toString())
        }
    }

    override suspend fun signUpWithGoogle(
        idToken: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (!it.result.additionalUserInfo!!.isNewUser) {
                googleClient.signOut()
                auth.signOut()
                return@addOnCompleteListener onFailure()
            }
            onSuccess()
        }
    }
}