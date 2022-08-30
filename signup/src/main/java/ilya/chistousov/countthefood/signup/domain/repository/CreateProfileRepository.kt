package ilya.chistousov.countthefood.signup.domain.repository

import androidx.lifecycle.LiveData
import ilya.chistousov.countthefood.core.model.Goal

interface CreateProfileRepository {
    suspend fun putString(key: String, value: String)
    suspend fun putInt(key: String, value: Int)
    suspend fun putFloat(key: String, value: Float)

    fun getGoal() : LiveData<Goal>

    suspend fun createProfile()
}