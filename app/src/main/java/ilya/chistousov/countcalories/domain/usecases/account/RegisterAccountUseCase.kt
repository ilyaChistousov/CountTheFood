package ilya.chistousov.countcalories.domain.usecases.account

import ilya.chistousov.countcalories.domain.repository.AccountRepository
import javax.inject.Inject

class RegisterAccountUseCase @Inject constructor(private val repository: AccountRepository) {

    suspend operator fun invoke(email: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        repository.signUpWithEmailAndPassword(email, password, onSuccess, onFailure)
    }
}