package ilya.chistousov.countthefood.signin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import ilya.chistousov.countthefood.signin.domain.repository.SignInRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository,
    val googleSignInClient: GoogleSignInClient
) : ViewModel() {

    fun signInWithEmailAndPassword(email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            signInRepository.signInWithEmailAndPassword(email, password,onSuccess, onFailure)
        }
    }

    fun signInWithGoogle(idToken: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            signInRepository.signInWithGoogle(idToken, onSuccess, onFailure)
        }
    }

    fun resetPassword(email: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            signInRepository.resetPassword(email, onSuccess, onFailure)
        }
    }
}
