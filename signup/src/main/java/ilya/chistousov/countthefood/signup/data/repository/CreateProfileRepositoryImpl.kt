package ilya.chistousov.countthefood.signup.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import ilya.chistousov.counthefood.database.dao.ProfileDao
import ilya.chistousov.counthefood.database.entity.ProfileEntity
import ilya.chistousov.countthefood.api.dto.ProfileDto
import ilya.chistousov.countthefood.api.service.ProfileService
import ilya.chistousov.countthefood.core.mapper.BaseMapper
import ilya.chistousov.countthefood.core.model.ActivityLevel
import ilya.chistousov.countthefood.core.model.Gender
import ilya.chistousov.countthefood.core.model.Goal
import ilya.chistousov.countthefood.core.model.Profile
import ilya.chistousov.countthefood.signup.domain.repository.CreateProfileRepository
import ilya.chistousov.countthefood.signup.utils.*
import javax.inject.Inject

class CreateProfileRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val dao: ProfileDao,
    private val profileEntityMapper: BaseMapper<ProfileEntity, Profile>,
    private val profileDtoMapper: BaseMapper<ProfileDto, Profile>,
    private val profileService: ProfileService
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
            sharedPreferences.getString(BIRTH_DATE, "")!!,
            ActivityLevel.valueOf(sharedPreferences.getString(ACTIVITY_LEVEL, null)!!),
            sharedPreferences.getInt(CURRENT_GROWTH, 0),
            sharedPreferences.getFloat(CURRENT_WEIGHT, 0F),
            sharedPreferences.getFloat(DESIRED_WEIGHT, 0F),
            sharedPreferences.getString(EMAIL, "")!!
        )
        dao.createProfile(profileEntityMapper.mapFromModelToEntity(profile))
        profileService.createProfile(profileDtoMapper.mapFromModelToEntity(profile))
    }
}