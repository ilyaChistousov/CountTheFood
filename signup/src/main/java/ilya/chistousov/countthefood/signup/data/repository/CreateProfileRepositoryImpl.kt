package ilya.chistousov.countthefood.signup.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import ilya.chistousov.countthefood.core.model.ActivityLevel
import ilya.chistousov.countthefood.core.model.Gender
import ilya.chistousov.countthefood.core.model.Goal
import ilya.chistousov.countthefood.core.model.Profile
import ilya.chistousov.countthefood.signup.data.dao.CreateProfileDao
import ilya.chistousov.countthefood.signup.data.mapper.ProfileMapper
import ilya.chistousov.countthefood.signup.domain.repository.CreateProfileRepository
import ilya.chistousov.countthefood.signup.utils.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CreateProfileRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val dao: CreateProfileDao,
    private val mapper: ProfileMapper
) : CreateProfileRepository {

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
            LocalDate.parse(
                sharedPreferences.getString(BIRTH_DATE, null),
                DateTimeFormatter.BASIC_ISO_DATE
            ),
            ActivityLevel.valueOf(sharedPreferences.getString(ACTIVITY_LEVEL, null)!!),
            sharedPreferences.getInt(CURRENT_GROWTH, 0),
            sharedPreferences.getFloat(CURRENT_WEIGHT, 0F),
            sharedPreferences.getFloat(DESIRED_WEIGHT, 0F),
            sharedPreferences.getString(EMAIL, "")!!
        )
        dao.createProfile(mapper.mapFromModelToEntity(profile))
    }
}