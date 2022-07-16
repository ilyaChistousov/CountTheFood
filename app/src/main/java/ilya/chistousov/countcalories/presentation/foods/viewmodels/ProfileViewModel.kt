package ilya.chistousov.countcalories.presentation.foods.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ilya.chistousov.countcalories.domain.usecases.profile.GetProfileUseCase
import javax.inject.Inject

class ProfileViewModel(getProfileUseCase: GetProfileUseCase) : ViewModel() {

    val currentProfile = getProfileUseCase()

}


class ProfileViewModelFactory @AssistedInject constructor(private val getProfileUseCase: GetProfileUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(getProfileUseCase) as T
    }

    @AssistedFactory
    interface Factory {
        fun create() : ProfileViewModelFactory
    }
}