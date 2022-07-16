package ilya.chistousov.countcalories.presentation.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ilya.chistousov.countcalories.data.firebase.repository.RegisterAccountRepositoryImpl
import ilya.chistousov.countcalories.domain.usecases.account.RegisterAccountUseCase
import kotlinx.coroutines.launch

class RegisterAccountViewModel(
    private val registerAccountUseCase: RegisterAccountUseCase
) : ViewModel() {
    fun registerAccount(email: String, password: String) {
        viewModelScope.launch {
            registerAccountUseCase(email, password)
        }
    }
}

class RegisterAccountViewModelFactory @AssistedInject constructor(
    private val registerAccountUseCase: RegisterAccountUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterAccountViewModel(registerAccountUseCase) as T
    }

    @AssistedFactory
    interface Factory {
        fun create() : RegisterAccountViewModelFactory
    }
}