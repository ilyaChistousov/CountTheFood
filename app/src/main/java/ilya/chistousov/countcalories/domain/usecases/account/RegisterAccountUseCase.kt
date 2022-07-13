package ilya.chistousov.countcalories.domain.usecases.account

import ilya.chistousov.countcalories.domain.repository.RegisterAccountRepository

class RegisterAccountUseCase(private val repository: RegisterAccountRepository) {

    suspend operator fun invoke(email: String, password: String) {
        repository.registerAccount(email, password)
    }
}