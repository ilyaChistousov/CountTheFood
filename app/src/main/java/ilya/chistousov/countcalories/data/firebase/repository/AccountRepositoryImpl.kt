package ilya.chistousov.countcalories.data.firebase.repository

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import ilya.chistousov.countcalories.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val googleClient: GoogleSignInClient
) : AccountRepository {

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