package ilya.chistousov.countcalories.data.room.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import ilya.chistousov.countcalories.data.room.dao.ProfileDao
import ilya.chistousov.countcalories.data.room.mapper.ProfileMapper
import ilya.chistousov.countcalories.domain.model.ActivityLevel
import ilya.chistousov.countcalories.domain.model.Gender
import ilya.chistousov.countcalories.domain.model.Goal
import ilya.chistousov.countcalories.domain.model.Profile
import ilya.chistousov.countcalories.domain.repository.ProfileRepository
import ilya.chistousov.countcalories.util.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val dao: ProfileDao,
    private val mapper: ProfileMapper
) : ProfileRepository {

    private val edit = sharedPreferences.edit()

    override suspend fun putString(key: String, value: String) {
        edit.putString(key, value)
        edit.apply()
    }

    override suspend fun putInt(key: String, value: Int) {
        edit.putInt(key, value)
        edit.apply()
    }

    override suspend fun putFloat(key: String, value: Float) {
        edit.putFloat(key, value)
        edit.apply()
    }

    override fun getGoal(): LiveData<Goal> {
        return liveData {
            emit(Goal.valueOf(sharedPreferences.getString(GOAL, Goal.WEIGHT_LOSS.name)!!))
        }
    }

    override suspend fun createProfile() {
        val profile = Profile(
            Gender.valueOf(sharedPreferences.getString(GENDER, null)!!),
            Goal.valueOf(sharedPreferences.getString(GOAL, null)!!),
            LocalDate.parse(sharedPreferences.getString(BIRTH_DATE, null), DateTimeFormatter.BASIC_ISO_DATE),
            ActivityLevel.valueOf(sharedPreferences.getString(ACTIVITY_LEVEL, null)!!),
            sharedPreferences.getInt(CURRENT_GROWTH, 0),
            sharedPreferences.getFloat(CURRENT_WEIGHT, 0F),
            sharedPreferences.getFloat(DESIRED_WEIGHT, 0F)
        )
        dao.createProfile(mapper.mapFromModelToEntity(profile))
    }

    override fun getProfile(): LiveData<Profile> {
        return Transformations.map(dao.getProfile())
        { mapper.mapFromEntityToModel(it) }
    }
}