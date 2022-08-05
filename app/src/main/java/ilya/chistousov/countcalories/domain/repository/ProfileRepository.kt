package ilya.chistousov.countcalories.domain.repository

import androidx.lifecycle.LiveData
import ilya.chistousov.countcalories.domain.model.ActivityLevel
import ilya.chistousov.countcalories.domain.model.Gender
import ilya.chistousov.countcalories.domain.model.Goal
import ilya.chistousov.countcalories.domain.model.Profile
import java.time.LocalDate

interface ProfileRepository {

    suspend fun putString(key: String, value: String)
    suspend fun putInt(key: String, value: Int)
    suspend fun putFloat(key: String, value: Float)

    fun getGoal() : LiveData<Goal>

    suspend fun createProfile()

    fun getProfile() : LiveData<Profile>
}