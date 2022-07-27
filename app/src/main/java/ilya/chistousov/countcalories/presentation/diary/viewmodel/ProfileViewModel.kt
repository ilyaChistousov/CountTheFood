package ilya.chistousov.countcalories.presentation.diary.viewmodel

import androidx.lifecycle.ViewModel
import ilya.chistousov.countcalories.domain.usecases.profile.GetProfileUseCase
import javax.inject.Inject

class ProfileViewModel @Inject constructor(getProfileUseCase: GetProfileUseCase) : ViewModel() {

    val currentProfile = getProfileUseCase()
}
