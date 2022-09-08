package ilya.chistousov.countthefood.food.domain.repository

import androidx.lifecycle.LiveData
import ilya.chistousov.countthefood.core.model.Profile

interface ProfileRepository {

    fun getProfile() : LiveData<Profile>
}