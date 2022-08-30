package ilya.chistousov.countthefood.food.presentation.diary.viewmodel

import androidx.lifecycle.ViewModel
import ilya.chistousov.countthefood.food.domain.repository.GetProfileRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(repository: GetProfileRepository) : ViewModel() {

    val currentProfile = repository.getProfile()
}
