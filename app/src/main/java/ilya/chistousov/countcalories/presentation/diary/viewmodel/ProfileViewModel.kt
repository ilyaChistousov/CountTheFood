package ilya.chistousov.countcalories.presentation.diary.viewmodel

import androidx.lifecycle.ViewModel
import ilya.chistousov.countcalories.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

    val currentProfile = repository.getProfile()
}
