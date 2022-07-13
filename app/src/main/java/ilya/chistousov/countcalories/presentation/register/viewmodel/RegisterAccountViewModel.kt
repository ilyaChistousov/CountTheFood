package ilya.chistousov.countcalories.presentation.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ilya.chistousov.countcalories.data.firebase.repository.RegisterAccountRepositoryImpl
import ilya.chistousov.countcalories.domain.usecases.account.RegisterAccountUseCase
import kotlinx.coroutines.launch

class RegisterAccountViewModel : ViewModel() {

    private val repository = RegisterAccountRepositoryImpl()
    private val registerAccountUseCase = RegisterAccountUseCase(repository)

    fun registerAccount(email: String, password: String) {
        viewModelScope.launch {
            registerAccountUseCase(email, password)
        }
    }
}