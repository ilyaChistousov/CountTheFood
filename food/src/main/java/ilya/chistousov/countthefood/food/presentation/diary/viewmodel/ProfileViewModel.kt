package ilya.chistousov.countthefood.food.presentation.diary.viewmodel

import androidx.lifecycle.ViewModel
import ilya.chistousov.countthefood.food.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(repository: ProfileRepository) : ViewModel() {

    val currentProfile = repository.getProfile()
}
