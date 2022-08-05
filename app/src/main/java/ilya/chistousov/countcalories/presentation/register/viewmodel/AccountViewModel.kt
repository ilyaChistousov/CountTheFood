package ilya.chistousov.countcalories.presentation.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import ilya.chistousov.countcalories.domain.repository.AccountRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    val googleSignInClient: GoogleSignInClient
) : ViewModel() {

    fun signUpWithGoogle(idToken: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            accountRepository.signUpWithGoogle(idToken, onSuccess, onFailure)
        }
    }

    fun signUpWithEmailAndPassword(email: String, password: String, onSuccess: () -> Unit,  onFailure: () -> Unit) {
        viewModelScope.launch {
            accountRepository.signUpWithEmailAndPassword(email, password, onSuccess, onFailure)
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            accountRepository.signInWithEmailAndPassword(email, password,onSuccess, onFailure)
        }
    }

    fun signInWithGoogle(idToken: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            accountRepository.signInWithGoogle(idToken, onSuccess, onFailure)
        }
    }

    fun resetPassword(email: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            accountRepository.resetPassword(email, onSuccess, onFailure)
        }
    }
}
