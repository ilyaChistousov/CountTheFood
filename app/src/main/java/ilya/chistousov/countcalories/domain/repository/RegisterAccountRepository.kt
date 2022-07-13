package ilya.chistousov.countcalories.domain.repository

interface RegisterAccountRepository {

    suspend fun registerAccount(email: String, password: String)
}