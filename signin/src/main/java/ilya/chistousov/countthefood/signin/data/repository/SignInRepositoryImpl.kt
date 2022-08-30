package ilya.chistousov.countthefood.signin.data.repository

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import ilya.chistousov.countthefood.signin.domain.repository.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val googleClient: GoogleSignInClient
) : SignInRepository {

    override suspend fun signUpWithEmailAndPassword(
        email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener {
            onFailure()
        }
    }

    override suspend fun signUpWithGoogle(idToken: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
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

    override suspend fun signInWithGoogle(idToken: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.result.additionalUserInfo!!.isNewUser) {
                it.result.user!!.delete()
                googleClient.signOut()
                auth.signOut()
                return@addOnCompleteListener onFailure()
            }
            onSuccess()
        }
    }

    override suspend fun signInWithEmailAndPassword(
        email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) onSuccess()
        }.addOnFailureListener {
            onFailure()
        }
    }

    override suspend fun resetPassword(email: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        auth.sendPasswordResetEmail(email).addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener {
            onFailure()
        }
    }
}