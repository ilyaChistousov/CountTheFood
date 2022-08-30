package ilya.chistousov.countthefood.signup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import ilya.chistousov.countthefood.signup.domain.repository.SignUpRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository,
    val googleSignInClient: GoogleSignInClient
) : ViewModel() {

    fun signUpWithGoogle(idToken: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            signUpRepository.signUpWithGoogle(idToken, onSuccess, onFailure)
        }
    }

    fun signUpWithEmailAndPassword(email: String, password: String, onSuccess: () -> Unit,  onFailure: () -> Unit) {
        viewModelScope.launch {
            signUpRepository.signUpWithEmailAndPassword(email, password, onSuccess, onFailure)
        }
    }
}