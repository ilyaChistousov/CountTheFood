package ilya.chistousov.countcalories.presentation.diary.viewmodel

import androidx.lifecycle.ViewModel
import ilya.chistousov.countcalories.domain.usecases.profile.GetProfileUseCase

class ProfileViewModel(getProfileUseCase: GetProfileUseCase) : ViewModel() {

    val currentProfile = getProfileUseCase()

}
