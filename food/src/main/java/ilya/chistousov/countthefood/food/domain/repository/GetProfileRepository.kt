package ilya.chistousov.countthefood.food.domain.repository

import androidx.lifecycle.LiveData
import ilya.chistousov.countthefood.core.model.Profile

interface GetProfileRepository {

    fun getProfile() : LiveData<Profile>
}