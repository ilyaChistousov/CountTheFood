package ilya.chistousov.countcalories.domain.repository

import androidx.lifecycle.LiveData
import ilya.chistousov.countcalories.domain.model.Profile

interface ProfileRepository {

    suspend fun createProfile(profile: Profile)

    fun getProfile() : LiveData<Profile>
}