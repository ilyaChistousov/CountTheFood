package ilya.chistousov.countcalories.domain.usecases.account

import ilya.chistousov.countcalories.domain.repository.RegisterAccountRepository
import javax.inject.Inject

class RegisterAccountUseCase @Inject constructor(private val repository: RegisterAccountRepository) {

    suspend operator fun invoke(email: String, password: String) {
        repository.registerAccount(email, password)
    }
}